package com.json.homework.repositories;

import com.json.homework.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //Query 3/ Ex 3
    @Query("select c from Category c order by c.products.size")
    List<Category> findAllOrderByProductsCount();
}
