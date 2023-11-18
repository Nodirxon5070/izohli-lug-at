package com.company.Izohli.lug.at.dto.responseDto;

import com.company.Izohli.lug.at.module.DayWord;
import com.company.Izohli.lug.at.module.Note;
import com.company.Izohli.lug.at.module.WordInSentence;
import com.company.Izohli.lug.at.module.WordType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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

    private Set<WordType> wordType;

    private Set<Note> notes;

    private Set<WordInSentence> wordInSentences;

    private Set<DayWord> dayWords;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
