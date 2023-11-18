package com.company.Izohli.lug.at.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;

    private List<ErrorDto> errorList;
}
