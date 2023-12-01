package com.company.Izohli.lug.at.dto.responseDto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordDto {
    private Integer wordId;
    private String label;
    private String transcript;

    private Integer categoryId;
    private Integer audioId;

    private Integer numView;
    private Integer numLike;
    private Integer numShare;

    private AudioDto audio;

    private CategoryDto category;

    private Set<WordTypeDto> wordType;

    private Set<NoteDto> notes;

    private Set<WordInSentenceDto> wordInSentences;

    private Set<DayWordDto> dayWords;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
