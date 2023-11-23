package com.company.Izohli.lug.at.dto.responseDto;

import com.company.Izohli.lug.at.module.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Locale;
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

    private CategoryDto category;
    private AudioDto audio;

    private Set<WordTypeDto> wordType;

    private Set<NoteDto> notes;

    private Set<WordInSentenceDto> wordInSentences;

    private Set<DayWordDto> dayWords;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
