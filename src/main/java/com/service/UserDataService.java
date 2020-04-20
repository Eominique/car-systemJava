package com.service;

import com.en.Color;
import com.en.SortCriterion;
import com.exception.UserDataException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class UserDataService {

    private UserDataService() {
    }

    private final static Scanner sc = new Scanner(System.in);

    public static int getInteger(String message) {
        System.out.println(message);
        String value = sc.nextLine();
        if (!value.matches("\\d+")) {
            throw new UserDataException("Incorrect value");
        }
        return Integer.parseInt(value);
    }

    public static BigDecimal getDecimal(String message){
        System.out.println(message);
        String value=sc.nextLine();

        if(!value.matches("(\\d+\\.)?\\d+")){
            throw new UserDataException("decimal value is not correct");
        }
        return new BigDecimal(value);
    }


public static double getDouble(String message){
    System.out.println(message);
    String value=sc.nextLine();
    if(!value.matches("(\\d+\\.)?\\d+")){
        throw new UserDataException("Incorrect input");
    }
    return Double.parseDouble(value);
}




public static boolean getBoolean(String message){
    System.out.println(message + " [y/n]");
    String value = sc.nextLine();
    char descision=value.charAt(0);
    return Character.toLowerCase(descision)=='y';
}

    public static Color getColor() {
        AtomicInteger counter = new AtomicInteger();
        String colorsList = Arrays
                .asList(Color.values())
                .stream()
                .map(color -> counter.incrementAndGet() + ". " + color.toString())
                .collect(Collectors.joining("\n"));
        System.out.println(colorsList);
        int decision;
        do {
            decision = getInteger("Choose correct color number:");
        } while (decision < 1 || decision > Color.values().length);
        return Color.values()[decision - 1];
    }

    public static SortCriterion getSortCriterion() {
        AtomicInteger counter = new AtomicInteger();
        String sortCriterionList = Arrays
                .asList(SortCriterion.values())
                .stream()
                .map(criterion -> counter.incrementAndGet() + " . " + criterion.toString())
                .collect(Collectors.joining("\n"));
        System.out.println(sortCriterionList);
        int decision;
        do {
            decision = getInteger("Choose correct sort criterion number: ");
        } while (decision < 1 || decision > SortCriterion.values().length);
        return SortCriterion.values()[decision - 1];
    }


    public static void close() {
        if (sc != null) {
            sc.close();
        }
    }


}
