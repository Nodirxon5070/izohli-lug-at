package com.company.Izohli.lug.at.dto.requestDto;

import com.company.Izohli.lug.at.dto.responseDto.WordTypeDto;
import com.company.Izohli.lug.at.module.WordType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestTypeDto {
    @NotBlank(message = "Name cannot be null or empty")
    private String name;
    @NotNull(message = "Order cannot be null or empty")
    private Integer order;

    private Set<WordTypeDto> wordType;
}
