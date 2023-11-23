package com.company.Izohli.lug.at.dto.responseDto;

import com.company.Izohli.lug.at.module.Word;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
    private Integer noteId;

    private Integer wordId;

    private String title;
    private String description;
    private String source;

    private WordDto word;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
