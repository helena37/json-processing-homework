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

import javax.validation.ConstraintViolation;
import java.util.Arrays;

@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final ValidationUtil validationUtil;
    private UserService userService;
    private CategoryService categoryService;

    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.validationUtil = validationUtil;
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
