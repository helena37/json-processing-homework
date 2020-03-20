package com.json.homework.controllers;

import com.google.gson.Gson;
import com.json.homework.models.dtos.CategorySeedDto;
import com.json.homework.models.dtos.ProductSeedDto;
import com.json.homework.models.dtos.UserSeedDto;
import com.json.homework.services.api.CategoryService;
import com.json.homework.services.api.ProductService;
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
    private final ProductService productService;

    @Autowired
    public AppController(Gson gson, CategoryService categoryService, UserService userService, ProductService productService) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedCategories();
        this.seedUsers();
        this.seedProducts();
    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[] productSeedDtos = this.gson
                .fromJson(new FileReader(PRODUCTS_FILE_PATH), ProductSeedDto[].class);
        this.productService.seedProduct(productSeedDtos);
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
