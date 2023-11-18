package com.company.Izohli.lug.at.dto.requestDto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestWordDto {
    private String label;
    private String transcript;
    private Integer numView;
    private Integer numLike;
    private Integer numShare;
}
