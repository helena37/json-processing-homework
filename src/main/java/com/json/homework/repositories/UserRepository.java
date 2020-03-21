package com.json.homework.repositories;

import com.json.homework.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    //Query 1/ Ex 1
    List<User> findAllByBuyerProductsIsNotNullOrderByLastNameAscFirstNameAsc();
}