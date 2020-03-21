package com.json.homework.repositories;

import com.json.homework.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findAllByBuyerProductsIsNotNullOrderByLastNameAscFirstNameAsc();
}