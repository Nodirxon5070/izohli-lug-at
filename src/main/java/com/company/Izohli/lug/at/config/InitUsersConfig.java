package com.company.Izohli.lug.at.config;

import com.company.Izohli.lug.at.module.Authorities;
import com.company.Izohli.lug.at.module.User;
import com.company.Izohli.lug.at.repository.AuthorityRepository;
import com.company.Izohli.lug.at.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class InitUsersConfig {


    private final UserRepository userRepository;


    private final AuthorityRepository authorityRepository;


    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void addUserAndAuthority() {

        this.authorityRepository.findByUsernameAndAuthority("Nodirxon", "ADMIN")
                .ifPresent(this.authorityRepository::delete);

        this.userRepository.findByUsernameAndEnabledIsTrue("Nodirxon")
                .ifPresent(this.userRepository::delete);


        User saveUser = this.userRepository.save(
                User.builder()
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .username("Nodirxon")
                        .password(passwordEncoder.encode("root"))
                        .build()
        );

        this.authorityRepository.save(
                Authorities.builder()
                        .username(saveUser.getUsername())
                        .authority("ADMIN")
                        .build()
        );


    }
}
