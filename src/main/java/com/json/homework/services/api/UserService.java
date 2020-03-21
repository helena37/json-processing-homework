package com.json.homework.services.api;

import com.json.homework.models.dtos.seedDtos.UserSeedDto;
import com.json.homework.models.dtos.viewDtos.UsersSoldProductsViewDto;
import com.json.homework.models.entities.User;

import java.util.List;

public interface UserService {
    //seed Users
    void seedUsers(UserSeedDto[] userSeedDtos);
    User  getRandomUser();
    //Ex 2
    List<UsersSoldProductsViewDto> getAllSellersWithSoldProducts();
}
