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
public class Sentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sentenceId;
    private String content;

    @OneToMany(mappedBy = "sentenceId"
            , fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<WordInSentence> wordInSentences;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
