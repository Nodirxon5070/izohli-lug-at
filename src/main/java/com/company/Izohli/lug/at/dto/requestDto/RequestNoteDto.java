package com.company.Izohli.lug.at.dto.requestDto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestNoteDto {
    private String title;
    private String description;
    private String source;
}
