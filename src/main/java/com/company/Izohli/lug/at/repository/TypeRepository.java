package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {
    Optional<Type> findByTypeId(Integer typeId);

    @Query(
            value = "select *\n" +
                    " from type as t where type_id=?1 and deleted_at is null ",
            nativeQuery = true
    )
    Type findTypeBYTypeId(Integer id);

}
