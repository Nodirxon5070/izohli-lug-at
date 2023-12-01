package com.company.Izohli.lug.at.validation;

import com.company.Izohli.lug.at.dto.ErrorDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestUserDto;
import com.company.Izohli.lug.at.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidation {

    private final UserRepository userRepository;

    public List<ErrorDto> userValid(RequestUserDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (this.userRepository.existsByUsernameAndEnabledIsTrue(dto.getUsername())) {
            errors.add(new ErrorDto("username", String.format("Username already exists. Rejection value :: %s", dto.getUsername())));
        }

        return errors;
    }
}
