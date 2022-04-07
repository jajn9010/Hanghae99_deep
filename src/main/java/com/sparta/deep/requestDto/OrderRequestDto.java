package com.sparta.deep.requestDto;

import com.sparta.deep.model.OrderList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequestDto {
    private Long restaurantId;
    private List<OrderList> foods;
}
