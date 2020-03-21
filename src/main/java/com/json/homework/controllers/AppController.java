package com.json.homework.controllers;

import com.google.gson.Gson;
import com.json.homework.models.dtos.seedDtos.CategorySeedDto;
import com.json.homework.models.dtos.seedDtos.ProductSeedDto;
import com.json.homework.models.dtos.seedDtos.UserSeedDto;
import com.json.homework.models.dtos.viewDtos.CategoriesByProductsCountViewDto;
import com.json.homework.models.dtos.viewDtos.ProductInRangeViewDto;
import com.json.homework.models.dtos.viewDtos.UsersSoldProductsViewDto;
import com.json.homework.services.api.CategoryService;
import com.json.homework.services.api.ProductService;
import com.json.homework.services.api.UserService;
import com.json.homework.utils.FileIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.json.homework.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {
    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final FileIOUtil fileIOUtil;

    @Autowired
    public AppController(Gson gson, CategoryService categoryService, UserService userService, ProductService productService, FileIOUtil fileIOUtil) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedCategories();
        this.seedUsers();
        this.seedProducts();
        this.writeProductInRange();
        this.writeSellersAndProducts();
        this.writeCategoriesByProducts();
    }

    private void writeCategoriesByProducts() throws IOException {
        List<CategoriesByProductsCountViewDto> categories =
                this.categoryService.getAllByProductsCount();
        String json = this.gson.toJson(categories);
        this.fileIOUtil.write(json, Ex_3_OUTPUT);
    }

    private void writeSellersAndProducts() throws IOException {
        List<UsersSoldProductsViewDto> usersSoldProductsViewDtos =
                this.userService.getAllSellersWithSoldProducts();
        String json = this.gson.toJson(usersSoldProductsViewDtos);
        this.fileIOUtil.write(json, Ex_2_OUTPUT);
    }

    private void writeProductInRange() throws IOException {
        List<ProductInRangeViewDto> productInRangeViewDtos =
                this.productService.getAllProductsInRange();
        String jsons = this.gson.toJson(productInRangeViewDtos);
        this.fileIOUtil.write(jsons, Ex_1_OUTPUT);
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
