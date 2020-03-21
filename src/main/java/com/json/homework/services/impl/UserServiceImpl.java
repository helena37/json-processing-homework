package com.json.homework.services.impl;

import com.json.homework.models.dtos.seedDtos.UserSeedDto;
import com.json.homework.models.dtos.viewDtos.ProductsNameAndPriceBuyerFirstAndLastName;
import com.json.homework.models.dtos.viewDtos.UsersSoldProductsViewDto;
import com.json.homework.models.entities.User;
import com.json.homework.repositories.UserRepository;
import com.json.homework.services.api.UserService;
import com.json.homework.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        if (this.userRepository.count() != 0) {
            return;
        }

        Arrays
                .stream(userSeedDtos)
                .forEach(userSeedDto -> {
                    if (this.validationUtil.isValid(userSeedDto)) {
                        User user = this.modelMapper.map(userSeedDto, User.class);
                        this.userRepository.saveAndFlush(user);
                    } else {
                        this.validationUtil
                                .getViolations(userSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });

    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        long randomId = random.nextInt((int) this.userRepository.count()) + 1;

        return this.userRepository.getOne(randomId);
    }

    @Override
    public List<UsersSoldProductsViewDto> getAllSellersWithSoldProducts() {
        return this.userRepository
                .findAllByBuyerProductsIsNotNullOrderByLastNameAscFirstNameAsc()
                .stream()
                .map(user -> {
                   UsersSoldProductsViewDto userDto =
                           this.modelMapper.map(user, UsersSoldProductsViewDto.class);

                   userDto.setSoldProducts(
                           user.getSellerProducts()
                           .stream()
                                   .filter(s -> s.getBuyer() != null)
                           .map(order -> this.modelMapper.map(order, ProductsNameAndPriceBuyerFirstAndLastName.class))
                           .collect(Collectors.toSet()));
                    return userDto;
                })
                .collect(Collectors.toList());
    }
}
