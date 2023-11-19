package com.company.Izohli.lug.at.dto.requestDto;

import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.module.WordInSentence;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestSentenceDto {
    @NotBlank(message = "Content cannot be null or empty")
    private String content;

    private Set<WordInSentenceDto> wordInSentences;
}
