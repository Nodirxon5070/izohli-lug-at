package com.company.Izohli.lug.at.dto.responseDto;

import com.company.Izohli.lug.at.module.Sentence;
import com.company.Izohli.lug.at.module.Word;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordInSentenceDto {
    private Integer wordInSentenceId;

    private Integer wordId;
    private Integer sentenceId;
    private Integer orders;

    private WordDto word;

    private SentenceDto sentence;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
