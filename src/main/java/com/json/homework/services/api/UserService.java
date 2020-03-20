package com.json.homework.services.api;

import com.json.homework.models.dtos.UserSeedDto;
import com.json.homework.models.entities.User;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);
    User  getRandomUser();
}
