package com.json.homework.services.api;

import com.json.homework.models.dtos.CategorySeedDto;
import com.json.homework.models.entities.Category;

import java.util.List;


public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDtos);
    List<Category> getRandomCategories();
}
