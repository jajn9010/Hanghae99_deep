package com.sparta.deep.requestDto;

import com.sparta.deep.model.OrderList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FoodOrderDto {
    private String name;
    private int quantity;
    private int price;

    public FoodOrderDto(OrderList orderList) {
        this.name = orderList.getName();
        this.quantity = orderList.getQuantity();
        this.price = orderList.getPrice();
    }
}
