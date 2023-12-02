package com.company.Izohli.lug.at.module;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dayWord")
public class DayWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dayWordId;
    @Column(name = "word_id")
    private Integer wordId;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id",insertable = false,updatable = false)
    private Word word;
}
