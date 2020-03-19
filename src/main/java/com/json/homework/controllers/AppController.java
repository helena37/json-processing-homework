package com.json.homework.controllers;

import com.google.gson.Gson;
import com.json.homework.models.dtos.CategorySeedDto;
import com.json.homework.models.dtos.UserSeedDto;
import com.json.homework.services.api.CategoryService;
import com.json.homework.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.json.homework.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {
    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public AppController(Gson gson, CategoryService categoryService, UserService userService) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedCategories();
        this.seedUsers();
    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedDto[] userSeedDtos = this.gson
                .fromJson(new FileReader(USERS_FILE_PATH), UserSeedDto[].class);
        this.userService.seedUsers(userSeedDtos);
    }

    private void seedCategories() throws FileNotFoundException {
        CategorySeedDto[] categorySeedDtos = this.gson
                       .fromJson(new FileReader(CATEGORIES_FILE_PATH), CategorySeedDto[].class);
        this.categoryService.seedCategories(categorySeedDtos);
    }

}
