package com.company.Izohli.lug.at.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestNoteDto {
    @NotBlank(message = "Title cannot be null or empty")
    private String title;
    private String description;
    private String source;

    private Integer wordId;
}
