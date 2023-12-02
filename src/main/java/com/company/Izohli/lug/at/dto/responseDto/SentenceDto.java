package com.company.Izohli.lug.at.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SentenceDto {
    private Integer sentenceId;

    private String content;

    private WordInSentenceDto wordInSentences;
}
