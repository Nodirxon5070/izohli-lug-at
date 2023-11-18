package com.company.Izohli.lug.at.dto.responseDto;

import com.company.Izohli.lug.at.module.WordInSentence;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SentenceDto {
    private Integer sentenceId;

    @NotBlank(message = "Content cannot be null or empty")
    private String content;

    private Set<WordInSentence> wordInSentences;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
