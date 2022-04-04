package com.sparta.deep.service;
import com.sparta.deep.model.Restaurant;
import com.sparta.deep.repository.RestaurantRepository;
import com.sparta.deep.requestDto.RestaurantRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sparta.deep.model.ExceptionMessage.*;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant createRestaurants(RestaurantRequestDto requestDto) {
        int minCheck = requestDto.getMinOrderPrice();
        int feeCheck = requestDto.getDeliveryFee();

        Restaurant restaurant = new Restaurant(requestDto);

        if (minCheck < 1000 || minCheck > 100000) {
            throw new IllegalArgumentException(EXCEPTION_MIN_PRICE_ERROR);
        }
        else if(minCheck%100 != 0) {
            throw new IllegalArgumentException(EXCEPTION_MIN_UNIT_ERROR);
        }
        else if(feeCheck < 0 || feeCheck > 10000){
            throw new IllegalArgumentException(EXCEPTION_FEE_PRICE_ERROR);
        }
        else if(feeCheck%500 !=0) {
            throw new IllegalArgumentException(EXCEPTION_FEE_UNIT_ERROR);
        }
        return restaurantRepository.save(restaurant);
    }

}
