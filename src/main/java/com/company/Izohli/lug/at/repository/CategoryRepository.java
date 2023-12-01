package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByCategoryId(Integer categoryId);
    @Query(
            value = "select *\n" +
                    " from category as w where category_id=?1 and deleted_at is null ",
            nativeQuery = true
    )
    Category findBYCategoryId(Integer categoryId);

}
