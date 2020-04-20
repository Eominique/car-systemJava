package com.service;

import com.en.Color;
import com.en.SortCriterion;
import com.exception.CarsServiceException;
import com.model.Car;
import com.validation.CarValidator;
import com.validation.JsonCarsConverter;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarService {

    private final Set<Car> cars;

    public CarService(String filename) {
        this.cars = init(filename);
    }

private Set<Car> init(String filename){
        AtomicInteger counter = new AtomicInteger();
        return new JsonCarsConverter(filename)
                .fromJson()
                .orElseThrow(()->new CarsServiceException("cannot parse date in json file"))
                .stream()
                .filter(car -> {
                    CarValidator carValidator=new CarValidator();
                    Map<String, String> errors = carValidator.validate(car);
                    if (carValidator.hasErrors()) {
                        System.out.println("---------------------- Validation error -----------------");
                        System.out.println("---------------------- Car no. " + counter.get());
                        System.out.println(errors
                                .entrySet()
                                .stream()
                                .map(e -> e.getKey() + ": " + e.getValue())
                                .collect(Collectors.joining("\n"))
                        );
                        System.out.println("---------------------------------------------------------");
                    }
                    counter.incrementAndGet();
                    return !carValidator.hasErrors();
                }).collect(Collectors.toSet());

}


//public List<Car> sortCars(SortCriterion sortCriterion, boolean ascendingSort){
//        if(sortCriterion==null){
//            throw new CarsServiceException("sort criterion object is null");
//        }
//    Stream<Car> carStream = switch (sortCriterion) {
//        case COLOR -> cars.stream().sorted(Comparator.comparing(Car::getColor));
//        case MILEAGE -> cars.stream().sorted(Comparator.comparing(Car::getMileage));
//        case MODEL -> cars.stream().sorted(Comparator.comparing(Car::getModel));
//        default -> cars.stream().sorted(Comparator.comparing(Car::getPrice));
//    };
//        List<Car> sortedCars=carStream.collect(Collectors.toList());
//        if(!ascendingSort){
//            Collections.reverse(sortedCars);
//        }
//        return sortedCars;
//}


public List<Car> greaterMileage(double mileage){
        if(mileage<=0){
            throw new CarsServiceException("mileage value is not correct: " + mileage);
        }
    List<Car> list = new ArrayList<>();

        for (Car car : cars) {
        if (car.getMileage() > mileage) {
            list.add(car);
        }
    }
    return list;
}

public Map<Color, Long> carColors(){
        return cars.stream()
                .collect(Collectors.groupingBy(Car::getColor, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1,v2)->v1,
                        LinkedHashMap::new));
}







public List<Car> sortComponents(){
        return cars
                .stream()
                .peek(car -> car.setComponents(car.getComponents()
                .stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new))))
                .collect(Collectors.toList());
}


    @Override
    public String toString() {
        return cars.stream()
                .map(c->c.getModel()+ "($" +c.getPrice()+
                        ", mileage:" + c.getMileage()+
                        ", components: "+ c.getComponents())
                .collect(Collectors.joining("/n"));
    }


}
