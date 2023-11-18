package com.company.Izohli.lug.at.utill;

import com.company.Izohli.lug.at.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface SimpleCrud<K, RQ, RS> {
    ResponseDto<RS> createEntity(RQ entity);

    ResponseDto<RS> getEntity(K entityId);

    ResponseDto<RS> updateEntity(K entityId, RQ entity);

    ResponseDto<RS> deleteEntity(K entityId);
}
