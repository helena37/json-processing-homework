package com.json.homework.services.api;

import com.json.homework.models.dtos.seedDtos.CategorySeedDto;
import com.json.homework.models.entities.Category;

import java.util.Set;


public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDtos);
    Set<Category> getRandomCategories();
}
