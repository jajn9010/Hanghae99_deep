package com.sparta.deep.service;

import com.sparta.deep.model.Food;
import com.sparta.deep.model.FoodOrder;
import com.sparta.deep.model.OrderList;
import com.sparta.deep.model.Restaurant;
import com.sparta.deep.repository.FoodOrderRepository;
import com.sparta.deep.repository.FoodRepository;
import com.sparta.deep.repository.OrderListRepository;
import com.sparta.deep.repository.RestaurantRepository;
import com.sparta.deep.requestDto.FoodOrderDto;
import com.sparta.deep.requestDto.FoodOrderRequestDto;
import com.sparta.deep.requestDto.OrderDto;
import com.sparta.deep.requestDto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.sparta.deep.model.ExceptionMessage.EXCEPTION_QUANTITY_ERROR;
import static com.sparta.deep.model.ExceptionMessage.EXCEPTION_TOTAL_MIN_PRICE_ERROR;

@RequiredArgsConstructor
@Service
public class FoodOrderService {
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderListRepository orderListRepository;
    private final FoodOrderRepository foodOrderRepository;

    @Transactional
    public OrderDto createOrders(OrderRequestDto orderRequestDto) {
        Restaurant restaurant = getRestaurant(orderRequestDto);

        int totalPrice = 0;
        List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
        List<OrderList> orderItems = orderRequestDto.getFoods();
        List<OrderList> orderItemList = new ArrayList<>();

        for (OrderList tempOrderList : orderItems) {
            int quantity = tempOrderList.getQuantity();
            if(quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException(EXCEPTION_QUANTITY_ERROR);
            }

            Food food = getFood(tempOrderList);

            OrderList orderList = OrderList.builder()
                    .quantity(tempOrderList.getQuantity())
                    .name(food.getName())
                    .price(food.getPrice() * quantity)
                    .food(food)
                    .build();
            orderListRepository.save(orderList);
            FoodOrderDto foodOrderDto = new FoodOrderDto(orderList);
            foodOrderDtoList.add(foodOrderDto);
            totalPrice += food.getPrice() * quantity;
            orderItemList.add(orderList);
        }

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException(EXCEPTION_TOTAL_MIN_PRICE_ERROR);
        }

        int deliveryFee = restaurant.getDeliveryFee();

        totalPrice += deliveryFee;
        FoodOrder foodOrder = FoodOrder.builder()
                .restaurantName(restaurant.getName())
                .totalPrice(totalPrice)
                .foods(orderItemList)
                .build();

        foodOrderRepository.save(foodOrder);
        OrderDto orderDto = new OrderDto(foodOrder, deliveryFee, foodOrderDtoList);
        return orderDto;
    }

    private Food getFood(OrderList tempOrderList) {
        return foodRepository.findById(tempOrderList.getId()).orElseThrow(
                () -> new NullPointerException("음식을 찾을 수 없습니다.")
        );
    }

    private Restaurant getRestaurant(OrderRequestDto orderRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new NullPointerException("레스토랑을 찾을 수 없습니다.")
        );
        return restaurant;
    }

    @Transactional
    public List<OrderDto> finAllOrder() {
        List<OrderDto> orderDtoList = new ArrayList<>();

        List<FoodOrder> ordersList = foodOrderRepository.findAll();
        for (FoodOrder foodOrder : ordersList) {
            int deliveryFee = restaurantRepository.findByName(foodOrder.getRestaurantName()).getDeliveryFee();
            List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
            List<OrderList> orderItemList = orderListRepository.findAll();
            for (OrderList orderList : orderItemList) {
                FoodOrderDto foodOrderDto = new FoodOrderDto(orderList);
                foodOrderDtoList.add(foodOrderDto);
            }
            OrderDto orderDto = new OrderDto(foodOrder, deliveryFee, foodOrderDtoList);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}
