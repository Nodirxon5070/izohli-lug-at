package com.company.Izohli.lug.at.module;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wordInSentence")
public class WordInSentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wordInSentenceId;

    private Integer wordId;
    private Integer sentenceId;
    private Integer order;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id",insertable = false,updatable = false)
    private Word word;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "sentence",insertable = false,updatable = false)
    private Sentence sentence;

}
