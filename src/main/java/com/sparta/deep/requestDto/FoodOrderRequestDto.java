package com.sparta.deep.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FoodOrderRequestDto {
    private Long id;
    private int quantity;
}
