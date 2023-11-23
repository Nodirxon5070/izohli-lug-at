package com.company.Izohli.lug.at.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestWordInSentenceDto {
    @NotNull(message = "Orders cannot be null or empty")
    private Integer orders;

    private Integer wordId;
    private Integer sentenceId;
}
