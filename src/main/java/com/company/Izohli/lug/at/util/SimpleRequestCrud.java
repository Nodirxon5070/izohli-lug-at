package com.company.Izohli.lug.at.util;

import com.company.Izohli.lug.at.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface SimpleRequestCrud <K,RQ,RS> {
    ResponseEntity<ResponseDto<RS>> createEntity(RQ entity);
    ResponseEntity<ResponseDto<RS>> getEntity(K entityId);
    ResponseEntity<ResponseDto<RS>> updateEntity(K entityId,RQ entity);
    ResponseEntity<ResponseDto<RS>> deleteEntity(K entityId);

}
