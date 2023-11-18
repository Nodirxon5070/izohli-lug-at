package com.company.Izohli.lug.at.dto.responseDto;

import com.company.Izohli.lug.at.module.Word;
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
public class AudioDto {
    private Integer audioId;
    private String path;

    private Set<Word> words;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
