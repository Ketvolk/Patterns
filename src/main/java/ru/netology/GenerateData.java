package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class GenerateData {
    static Faker faker = new Faker(new Locale("ru"));

    public static String generateCity() {
        List cities = Arrays.asList("Москва", "Магадан", "Самара", "Майкоп", "Кострома", "Махачкала", "Великий Новгород", "Саратов", "Севастополь", "Симферополь", "Краснодар", "Красноярск", "Псков", "владивосток", "Смоленск", "Ставрополь", "Астрахань", "Тула", "Тамбов");

        int i = 0;
        String city = null;
        while (i == 0) {
            city = faker.address().city();
            if (cities.contains(city)) {
                i = 1;
            }
        }
        return city;
    }

    public static String generateDate() {
        int addDaysL = faker.number().numberBetween(3, 10_000);
        long addDays = (long) addDaysL;
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateName() {
        String name = faker.name().lastName() + " " + faker.name().firstName();
        return name;
    }

    public static String generatePhone() {
        String phoneGenerate = faker.number().digits(9);
        String phone = ("+79" + phoneGenerate);
        return phone;
    }
}
