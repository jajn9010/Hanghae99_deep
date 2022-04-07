package com.sparta.deep.controller;

import com.sparta.deep.model.FoodOrder;
import com.sparta.deep.repository.FoodOrderRepository;
import com.sparta.deep.requestDto.FoodOrderRequestDto;
import com.sparta.deep.requestDto.OrderDto;
import com.sparta.deep.requestDto.OrderRequestDto;
import com.sparta.deep.service.FoodOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodOrderController {
    private final FoodOrderRepository foodOrderRepository;
    private final FoodOrderService foodOrderService;

    @PostMapping("/order/request")
    public OrderDto createOrders (@RequestBody OrderRequestDto orderRequestDto) {
        return foodOrderService.createOrders(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderDto> findAllOrder() {
        return foodOrderService.finAllOrder();
    }
}
