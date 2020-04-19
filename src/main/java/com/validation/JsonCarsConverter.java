package com.validation;

import com.model.Car;

import java.util.List;

public class JsonCarsConverter extends JsonConverter<List<Car>> {
    public JsonCarsConverter(String jsonFilename) {
        super(jsonFilename);
    }
}