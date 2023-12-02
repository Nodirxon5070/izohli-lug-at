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
public class WordInSentenceDto {
    private Integer wordInSentenceId;

    private Integer wordId;
    private Integer sentenceId;
    private Integer orders;

    private WordDto word;

    private SentenceDto sentence;

}
