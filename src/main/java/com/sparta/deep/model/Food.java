package com.sparta.deep.model;

import com.sparta.deep.requestDto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Long restaurantId;

    public Food (Long id, FoodRequestDto requestDto){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurantId = id;
    }
}
