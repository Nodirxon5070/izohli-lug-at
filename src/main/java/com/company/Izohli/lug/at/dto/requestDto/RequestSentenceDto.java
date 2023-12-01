package com.company.Izohli.lug.at.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestSentenceDto {
    @NotBlank(message = "Content cannot be null or empty")
    private String content;

}
