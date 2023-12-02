package com.company.Izohli.lug.at.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AudioDto {
    private Integer audioId;
    private String path;

    private WordDto word;

    private String ext;
    private String audioName;
    private byte[] data;
}
