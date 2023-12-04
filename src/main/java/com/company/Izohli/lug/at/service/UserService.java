package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.LoginDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestRegisterConfirmDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestUserDto;
import com.company.Izohli.lug.at.dto.responseDto.TokenResponseDto;
import com.company.Izohli.lug.at.dto.responseDto.UserDto;
import com.company.Izohli.lug.at.module.Authorities;
import com.company.Izohli.lug.at.module.User;
import com.company.Izohli.lug.at.module.UserAccessSession;
import com.company.Izohli.lug.at.module.UserRefreshSession;
import com.company.Izohli.lug.at.repository.AuthorityRepository;
import com.company.Izohli.lug.at.repository.UserRefreshSessionRepository;
import com.company.Izohli.lug.at.repository.UserRepository;
import com.company.Izohli.lug.at.repository.UserAccessSessionRepository;
import com.company.Izohli.lug.at.security.JwtUtil;
import com.company.Izohli.lug.at.service.mapper.UserMapper;
import com.company.Izohli.lug.at.validation.UserValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final UserValidation userValidation;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final UserAccessSessionRepository userAccessSessionRepository;
    private final UserRefreshSessionRepository userRefreshSessionRepository;

    public ResponseDto<UserDto> register(RequestUserDto dto) {
        try {

            List<ErrorDto> errorList = this.userValidation.userValid(dto);
            if (!errorList.isEmpty()) {
                return ResponseDto.<UserDto>builder()
                        .code(-2)
                        .errorList(errorList)
                        .build();
            }


            User user = this.userMapper.toEntity(dto);
            user.setCreatedAt(LocalDateTime.now());

            User saveUser = this.userRepository.save(user);

            this.authorityRepository.save(
                    Authorities.builder()
                            .userId(saveUser.getId())
                            .username(saveUser.getUsername())
                            .authority("USER")
                            .createdAt(LocalDateTime.now())
                            .build()
            );

            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("OK")
                    .data(
                            this.userMapper.toDto(saveUser)
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message(String.format("User while saving error. Message %s", e.getMessage()))
                    .build();
        }
    }


    @Transactional
    public ResponseDto<TokenResponseDto> registerConfirm(RequestRegisterConfirmDto dto) {
        try {
            if (!dto.getCode().equals("0000")){
                return ResponseDto.<TokenResponseDto>builder()
                       .code(-2)
                       .message("Code is not valid!")
                       .build();
            }
            return this.userRepository.findByUsername(dto.getUsername())
                    .map(user -> {

                        if (user.getEnabled().equals(true)){
                            return ResponseDto.<TokenResponseDto>builder()
                                  .code(-1)
                                  .message(String.format("User with %s username is already enabled!", dto.getUsername()))
                                  .build();
                        }
                        user.setEnabled(true);
                        String sessionId = UUID.randomUUID().toString();
                        String token = jwtUtil.generateToken(sessionId);

                        checkValidToken(token);
                        saveUserSession(sessionId, this.userMapper.toDto(user));

                        return ResponseDto.<TokenResponseDto>builder()
                                .success(true)
                                .message("OK")
                                .data(TokenResponseDto.builder()
                                        .accessToken(this.jwtUtil.generateToken(token))
                                        .refreshToken(this.jwtUtil.generateToken(token))
                                        .build())
                                .build();

                    })
                    .orElse(ResponseDto.<TokenResponseDto>builder()
                            .code(-1)
                            .message(String.format("User with %s username is not found!", dto.getUsername()))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<TokenResponseDto>builder()
                    .code(-3)
                    .message(String.format("User while register confirm error. Message %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<TokenResponseDto> login(LoginDto dto) {
        try {
            return this.userRepository.findByUsernameAndEnabledIsTrue(dto.getUsername())
                    .map(user -> {
                        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                            return ResponseDto.<TokenResponseDto>builder()
                                    .code(-2)
                                    .message("Password is not valid!")
                                    .build();
                        }
                        String sessionId = UUID.randomUUID().toString();
                        String token = jwtUtil.generateToken(sessionId);

                        checkValidToken(token);
                        saveUserSession(sessionId, this.userMapper.toDto(user));

                        return ResponseDto.<TokenResponseDto>builder()
                                .success(true)
                                .message("Login success")
                                .data(TokenResponseDto.builder()
                                        .accessToken(token)
                                        .refreshToken(token)
                                        .timestamp(
                                                LocalDateTime.ofInstant(
                                                        Instant.ofEpochMilli(jwtUtil.getClaims("exp", token, Long.class) * 1000),
                                                        ZoneId.systemDefault()
                                                )
                                        ).build())
                                .build();
                    }).orElse(ResponseDto.<TokenResponseDto>builder()
                            .code(-1)
                            .message("Login failed")
                            .build());
        } catch (Exception e) {
            return ResponseDto.<TokenResponseDto>builder()
                    .code(-3)
                    .message(String.format("User while login error. Message %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<TokenResponseDto> refreshToken(String token) {
        if (!this.jwtUtil.isValid(token)) {
            return ResponseDto.<TokenResponseDto>builder()
                    .code(-3)
                    .message("Token is not valid!")
                    .build();
        }
        try {
            return this.userRefreshSessionRepository.findById(token)
                    .map(refreshSession -> {
                        checkValidToken(token);
                        User user = this.userMapper.toEntityResponseDto(refreshSession.getUserDto());
                        user.setEnabled(true);
                        String sessionId = UUID.randomUUID().toString();
                        saveUserSession(sessionId, this.userMapper.toDto(user));
                        return ResponseDto.<TokenResponseDto>builder()
                                .success(true)
                                .message("OK")
                                .data(TokenResponseDto.builder()
                                        .accessToken(this.jwtUtil.generateToken(sessionId))
                                        .refreshToken(this.jwtUtil.generateToken(sessionId))
                                        .build())
                                .build();

                    }).orElse(ResponseDto.<TokenResponseDto>builder()
                            .code(-1)
                            .message(String.format("User with token %s is not found!", token))
                            .build());

        } catch (Exception e) {
            return ResponseDto.<TokenResponseDto>builder()
                    .code(-3)
                    .message(String.format("User while Refreshing Token error. Message %s", e.getMessage()))
                    .build();
        }

    }


    public ResponseDto<UserDto> logout(String username) {
        return this.userRepository.findByUsernameAndEnabledIsTrue(username)
                .map(user -> {
                    user.setEnabled(false);
                    this.userRepository.save(user);
                    return ResponseDto.<UserDto>builder()
                            .success(true)
                            .message("OK")
                            .build();
                }).orElse(ResponseDto.<UserDto>builder()
                        .code(-1)
                        .message(String.format("User with %s username is not found!", username))
                        .build());
    }


    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsernameAndEnabledIsTrue(username)
                .map(this.userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User : " + username + " not found"));
    }


    private void checkValidToken(String token) {
        this.userAccessSessionRepository.findById(token)
                .ifPresent(this.userAccessSessionRepository::delete);

        this.userRefreshSessionRepository.findById(token)
                .ifPresent(this.userRefreshSessionRepository::delete);
    }

    private void saveUserSession(String sessionId, UserDto userDto) {
        this.userAccessSessionRepository.save(new UserAccessSession(
                        sessionId, userDto
                )
        );
        this.userRefreshSessionRepository.save(new UserRefreshSession(
                        sessionId, userDto
                )
        );
    }


}
