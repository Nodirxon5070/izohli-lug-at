package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByCategoryIdAndDeletedAtIsNull(Integer categoryId);

}
