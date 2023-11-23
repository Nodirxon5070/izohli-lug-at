package com.company.Izohli.lug.at.dto.responseDto;

import com.company.Izohli.lug.at.module.Word;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DayWordDto {
    private Integer dayWordId;
    private Integer wordId;

    private WordDto word;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
