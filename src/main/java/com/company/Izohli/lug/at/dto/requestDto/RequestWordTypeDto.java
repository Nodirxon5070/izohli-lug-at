package com.company.Izohli.lug.at.dto.requestDto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestWordTypeDto {

    private Integer typeId;
    private Integer wordId;
    private int[] wordIds;

}
