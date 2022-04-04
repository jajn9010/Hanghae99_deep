package com.sparta.deep.model;

import com.sparta.deep.requestDto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private List<Food> foods;

    @Column(nullable = false)
    private Long restaurantId;

    public FoodOrder (Long id, List<FoodRequestDto> foodRequestDtos) {

    }
}
