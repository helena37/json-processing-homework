package com.json.homework.services.api;

import com.json.homework.models.dtos.UserSeedDto;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);
}
