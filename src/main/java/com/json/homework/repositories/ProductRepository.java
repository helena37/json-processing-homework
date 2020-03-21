package com.json.homework.repositories;

import com.json.homework.models.entities.Category;
import com.json.homework.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Query 1/ Ex 1
    List<Product> findAllByPriceBetweenAndBuyerIsNull(BigDecimal priceLower, BigDecimal priceHigher);

    //Query 3/ Ex 3 - averagePrice
    @Query("select avg (p.price), c from Product p " +
            "join p.categories c " +
            "where c.name = :categoryName " +
            "group by c.id")
            //"group by c.name having :categoryName")
    BigDecimal getAveragePriceOnProductsInCategory(String categoryName);

    //Query 3/ Ex 3 - totalRevenue
    @Query("select sum (p.price) from Product p " +
            "join p.categories c " +
            "where c.name = :categoryName " +
            "group by c.id")
    BigDecimal findTotalRevenue(String categoryName);

}
