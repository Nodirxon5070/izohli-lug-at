package com.company.Izohli.lug.at.dto.requestDto;

import com.company.Izohli.lug.at.dto.responseDto.*;
import com.company.Izohli.lug.at.module.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestWordDto {
    @NotBlank(message = "Label cannot be null or empty")
    private String label;
    private String transcript;
    private Integer numView;
    private Integer numLike;
    private Integer numShare;

    private Integer categoryId;
    private Integer audioId;

    private AudioDto audio;

    private CategoryDto category;


}
