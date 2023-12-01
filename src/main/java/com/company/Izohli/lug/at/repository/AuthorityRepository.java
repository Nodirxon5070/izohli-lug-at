package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {

    Optional<Authorities> findByUsernameAndAuthority(String username, String auth);

    Optional<Authorities> findByIdAndDeletedAtIsNull(Integer authId);

}
