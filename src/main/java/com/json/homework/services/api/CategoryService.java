package com.json.homework.services.api;

import com.json.homework.models.dtos.CategorySeedDto;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDtos);
}
