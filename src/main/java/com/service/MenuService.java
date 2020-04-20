package com.service;

import com.en.SortCriterion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MenuService {

private final CarService carService;

    public MenuService(CarService carService) {
        this.carService = carService;
    }

    public void mainMenu() {
        while (true) {
            try {
                System.out.println("0. Exit");
                System.out.println("1. Show all cars");
                System.out.println("2. Sort cars");
                System.out.println("3. Find cars with greater mileage");
                System.out.println("4. Count cars with specific color");
                System.out.println("5. Find most expensive models");
                System.out.println("6. Show cars statistics");
                System.out.println("7. Most Expensive Car/s");
                System.out.println("8. Cars with sorted components");
                System.out.println("9. Components and car list");
                System.out.println("10. Cars in price range");
                int decision = UserDataService.getInteger("Choose option:");
                switch (decision) {
                    case 0:
                        System.out.println("Goodbye");
                        return;


                    case 1:
                        option1();
                        break;
                    case 2:
                        option2();
                        break;

//                    case 3 -> option3();
//                    case 4 -> option4();
//                    case 5 -> option5();
//                    case 6 -> option6();
//                    case 7 -> option7();
//                    case 8 -> option8();
//                    case 9 -> option9();
//                    case 10 -> option10();
                    default:
                        System.out.println("No option with this number");

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void option2() {
        SortCriterion sortCriterion=UserDataService.getSortCriterion();

    }

    private void option1() {
        System.out.println(toJson(carService));
    }


    private static <T> String toJson(T item){
    Gson gson=new GsonBuilder()
            .setPrettyPrinting()
            .create();

return gson.toJson(item);
    }




}
