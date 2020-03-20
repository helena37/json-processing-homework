package com.json.homework.services.impl;

import com.json.homework.models.dtos.seedDtos.CategorySeedDto;
import com.json.homework.models.entities.Category;
import com.json.homework.repositories.CategoryRepository;
import com.json.homework.services.api.CategoryService;
import com.json.homework.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
       if (this.categoryRepository.count() != 0) {
           return;
       }

       Arrays
               .stream(categorySeedDtos)
               .forEach(categorySeedDto -> {
                   if (this.validationUtil.isValid(categorySeedDto)) {
                       Category category = this.modelMapper.map(categorySeedDto, Category.class);
                       this.categoryRepository.saveAndFlush(category);
                   } else {
                       this.validationUtil
                               .getViolations(categorySeedDto)
                               .stream()
                               .map(ConstraintViolation::getMessage)
                               .forEach(System.out::println);

                   }
               });
    }

    @Override
    public Set<Category> getRandomCategories() {
        Random random = new Random();
        Set<Category> resultList = new HashSet<>();

        int randomCounter = random.nextInt(3) + 1;

        for (int i = 0; i < randomCounter; i++) {
            long randomId = random.nextInt((int) this.categoryRepository.count()) + 1;
            resultList.add(this.categoryRepository.getOne(randomId));
        }
        return resultList;
    }

}
