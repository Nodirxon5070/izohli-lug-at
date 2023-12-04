package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndEnabledIsTrue(String username);

    Optional<User> findByUsernameAndEnabledIsFalse(String username);


    Boolean existsByUsernameAndEnabledIsTrue(String username);

}
