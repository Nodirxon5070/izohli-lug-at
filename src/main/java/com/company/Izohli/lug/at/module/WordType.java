package com.company.Izohli.lug.at.module;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wordType")
public class WordType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wordTypeId;

    private Integer typeId;
    private Integer wordId;
    private int[] wordIds;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id",insertable = false,updatable = false)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id",insertable = false,updatable = false)
    private Word word;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
