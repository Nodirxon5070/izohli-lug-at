package com.company.Izohli.lug.at.util;

import com.company.Izohli.lug.at.dto.ResponseDto;

public interface SimpleCrud<K, RQ, RS> {
    ResponseDto<RS> createEntity(RQ entity);

    ResponseDto<RS> getEntity(K entityId);

    ResponseDto<RS> updateEntity(K entityId, RQ entity);

    ResponseDto<RS> deleteEntity(K entityId);
}
