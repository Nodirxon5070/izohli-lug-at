package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.UserSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends CrudRepository<UserSession, String> {
}
