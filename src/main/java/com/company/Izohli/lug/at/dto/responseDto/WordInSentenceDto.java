package com.company.Izohli.lug.at.dto.responseDto;

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
    @NotBlank(message = "Sentence cannot be null or empty")
    private Integer sentence;
    private Integer order;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
