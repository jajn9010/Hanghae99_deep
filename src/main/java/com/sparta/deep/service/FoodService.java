package com.sparta.deep.service;

import com.sparta.deep.model.Food;
import com.sparta.deep.repository.FoodRepository;
import com.sparta.deep.requestDto.FoodRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sparta.deep.model.ExceptionMessage.*;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public void createFoods(Long id, List<FoodRequestDto> requestDtoList){
        List<Food> foodList= foodRepository.findAllByRestaurantId(id);

        for (int i =0; i<foodList.size(); i++) {
            for (int j=i+1; j<requestDtoList.size(); j++){
                if(foodList.get(i).getName().equals(requestDtoList.get(j-1).getName())){
                    throw new IllegalArgumentException(EXCEPTION_SAME_MENU_ERROR);
                    }
                else if(requestDtoList.get(i).getName().equals(requestDtoList.get(j).getName())){
                    throw new IllegalArgumentException(EXCEPTION_DUPLE_MENU_ERROR);
                }
            }
        }

        for (FoodRequestDto requestDto: requestDtoList){
            int foodMinCheck = requestDto.getPrice();
            Food result = new Food(id, requestDto);

            if(foodMinCheck<100 || foodMinCheck>1000000) {
                throw new IllegalArgumentException(EXCEPTION_MIN_MENU_ERROR);
            }
            else if(foodMinCheck%100 != 0) {
                throw new IllegalArgumentException(EXCEPTION_UNIT_MENU_ERROR);
            }
            foodRepository.save(result);
        }
    }
}
