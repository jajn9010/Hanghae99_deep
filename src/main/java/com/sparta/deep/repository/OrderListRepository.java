package com.sparta.deep.repository;

import com.sparta.deep.model.FoodOrder;
import com.sparta.deep.model.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderListRepository extends JpaRepository <OrderList, Long> {
    List<OrderList> findOrderListByFoodOrder(FoodOrder foodOrder);
}
