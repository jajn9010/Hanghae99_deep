package com.sparta.deep.controller;

import com.sparta.deep.model.Restaurant;
import com.sparta.deep.repository.RestaurantRepository;
import com.sparta.deep.requestDto.RestaurantRequestDto;
import com.sparta.deep.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    public Restaurant createRestaurants(@RequestBody RestaurantRequestDto requestDto) {
        return restaurantService.createRestaurants(requestDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant() {
        return restaurantRepository.findAll();
    }
}
