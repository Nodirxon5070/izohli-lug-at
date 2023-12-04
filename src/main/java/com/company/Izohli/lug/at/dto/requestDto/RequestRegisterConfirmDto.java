package com.company.Izohli.lug.at.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRegisterConfirmDto {
    @NotBlank(message = "Username cannot be null or empty!")
    private String username;
    @NotBlank(message = "Code cannot be null or empty!")
    private String code;
}
