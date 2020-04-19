package com.model;

import com.en.Color;
import lombok.*;
import java.math.BigDecimal;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    private String model;
    private BigDecimal price;
    private Color color;
    private double mileage;
    private Set<String> components;
}
