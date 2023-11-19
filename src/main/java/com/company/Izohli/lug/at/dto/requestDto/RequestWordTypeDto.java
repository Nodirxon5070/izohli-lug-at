package com.company.Izohli.lug.at.dto.requestDto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestWordTypeDto {

    private Integer typeId;
    private Integer wordId;

}
