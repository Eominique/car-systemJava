package com;

import com.service.CarService;
import com.service.MenuService;

public class Main {
    public static void main(String[] args) {
        try{

    final String FILENAME="G:\\Project\\KWIECIEN\\20\\car-systemJava\\src\\main\\resources\\data\\cars.json";


    CarService carService=new CarService(FILENAME);
MenuService menuService=new MenuService(carService);
menuService.mainMenu();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
