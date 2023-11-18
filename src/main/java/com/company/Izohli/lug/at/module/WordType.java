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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
