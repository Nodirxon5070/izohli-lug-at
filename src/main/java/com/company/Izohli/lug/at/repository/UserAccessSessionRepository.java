package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.UserAccessSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessSessionRepository extends CrudRepository<UserAccessSession, String> {
}
