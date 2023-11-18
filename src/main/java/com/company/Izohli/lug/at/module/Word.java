package com.company.Izohli.lug.at.module;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "word")

public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wordId;
    private String label;
    private String transcript;

    private Integer categoryId;
    private Integer audioId;

    private Integer numView;
    private Integer numLike;
    private Integer numShare;

    @OneToMany(mappedBy = "wordId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<WordType> wordType;

    @OneToMany(mappedBy = "wordId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Note> notes;

    @OneToMany(mappedBy = "wordId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<WordInSentence> wordInSentences;

    @OneToMany(mappedBy = "wordId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<DayWord> dayWords;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
