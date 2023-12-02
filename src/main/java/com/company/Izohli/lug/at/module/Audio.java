package com.company.Izohli.lug.at.module;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer audioId;
    private String path;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Word word;

    private String ext;
    private String audioName;

}
