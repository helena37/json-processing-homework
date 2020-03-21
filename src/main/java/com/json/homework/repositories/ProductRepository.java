package com.json.homework.repositories;

import com.json.homework.models.dtos.viewDtos.UsersSoldProductsViewDto;
import com.json.homework.models.entities.Product;
import com.json.homework.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Query 1
    List<Product> findAllByPriceBetweenAndBuyerIsNull(BigDecimal priceLower, BigDecimal priceHigher);
}
