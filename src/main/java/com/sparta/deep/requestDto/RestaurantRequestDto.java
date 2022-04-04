package com.sparta.deep.requestDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantRequestDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
