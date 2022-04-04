package com.sparta.deep.controller;

import com.sparta.deep.model.Food;
import com.sparta.deep.repository.FoodRepository;
import com.sparta.deep.repository.RestaurantRepository;
import com.sparta.deep.requestDto.FoodRequestDto;
import com.sparta.deep.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodRepository foodRepository;
    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void createFoods (@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> requestDto) {
        foodService.createFoods(restaurantId, requestDto);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFood(@PathVariable Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
