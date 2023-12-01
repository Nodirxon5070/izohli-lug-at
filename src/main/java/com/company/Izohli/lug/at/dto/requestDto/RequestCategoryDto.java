package com.company.Izohli.lug.at.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCategoryDto {
    @NotBlank(message = "Name cannot be null or empty")
    private String name;
    private String description;

}
