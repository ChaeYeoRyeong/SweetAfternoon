package com.coffee.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class RecommendCoffeeDto {
    private String coffee;
    private String description;
    private String coffeeImg;
    private Integer radio1;
    private Integer radio2;
    private Integer radio3;

    public RecommendCoffeeDto() {}

    public RecommendCoffeeDto(String coffee, String description, String coffeeImg) {
        this.coffee = coffee;
        this.description = description;
        this.coffeeImg = coffeeImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecommendCoffeeDto coffeeDto = (RecommendCoffeeDto) o;
        return Objects.equals(coffee, coffeeDto.coffee) && Objects.equals(description, coffeeDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffee, description);
    }
}
