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
@Table(name = "word_in_sentence")
public class WordInSentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_in_sentence_id")
    private Integer wordInSentenceId;

    @Column(name = "word_id")
    private Integer wordId;

    @Column(name = "sentence_id")
    private Integer sentenceId;
    private Integer orders;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id",insertable = false,updatable = false)
    private Word word;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Sentence sentence;

}
