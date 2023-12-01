package com.company.Izohli.lug.at.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAudioDto {
    @NotBlank(message = "Path cannot be null or empty")
    private String path;
    private String ext;
    private String audioName;
}
