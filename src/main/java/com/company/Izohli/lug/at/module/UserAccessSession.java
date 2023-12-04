package com.company.Izohli.lug.at.module;

import com.company.Izohli.lug.at.dto.responseDto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(timeToLive = 60 * 60 * 24)
public class UserAccessSession {

    @Id
    private String sessionId;
    private UserDto userDto;

}
