package com.company.Izohli.lug.at.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestTypeDto {
    @NotBlank(message = "Name cannot be null or empty")
    private String name;
    @NotNull(message = "Orders cannot be null or empty")
    private Integer orders;

}
