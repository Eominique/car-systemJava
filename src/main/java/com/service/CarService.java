package com.service;

import com.model.Car;
import com.validation.JsonCarsConverter;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class CarService {

    private final Set<Car> cars;

    public CarService(String filename) {
        this.cars = init(filename);
    }

private Set<Car> init(String filename){
        AtomicInteger counter = new AtomicInteger();
        return new JsonCarsConverter(filename)
                .

}

}
