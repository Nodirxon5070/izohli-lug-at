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
@Table(name = "word_type")
public class WordType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_type_id")
    private Integer wordTypeId;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "word_id")
    private Integer wordId;
    private int[] wordIds;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id",insertable = false,updatable = false)
    private Word word;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
