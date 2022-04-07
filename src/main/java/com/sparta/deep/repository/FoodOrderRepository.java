package com.sparta.deep.repository;

import com.sparta.deep.model.Food;
import com.sparta.deep.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    List<FoodOrder> findAllById(Long id);
}
