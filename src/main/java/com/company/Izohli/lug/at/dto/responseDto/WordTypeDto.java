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
public class WordTypeDto {
    private Integer wordTypeId;

    private Integer typeId;
    private Integer wordId;
    private int[] wordIds;

    private TypeDto type;

    private WordDto word;
}
