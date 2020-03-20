package com.json.homework.services.impl;

import com.json.homework.models.dtos.ProductSeedDto;
import com.json.homework.models.entities.Product;
import com.json.homework.repositories.ProductRepository;
import com.json.homework.services.api.CategoryService;
import com.json.homework.services.api.ProductService;
import com.json.homework.services.api.UserService;
import com.json.homework.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Random;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, ValidationUtil validationUtil, UserService userService, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedProduct(ProductSeedDto[] productSeedDtos) {
        if (this.productRepository.count() != 0) {
            return;
        }

        Arrays
                .stream(productSeedDtos)
                .forEach(productSeedDto-> {
                    if (this.validationUtil.isValid(productSeedDto)) {
                        Product product = this.modelMapper.map(productSeedDto, Product.class);

                        product.setSeller(this.userService.getRandomUser());

                        Random random = new Random();
                        int randomNum = random.nextInt(2);

                        if (randomNum == 1) {
                            product.setBuyer(this.userService.getRandomUser());
                        }

                        product.setCategories(this.categoryService.getRandomCategories());
                        this.productRepository.saveAndFlush(product);
                    } else {
                        this.validationUtil
                                .getViolations(productSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }
}
