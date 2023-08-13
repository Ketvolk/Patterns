package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    static Faker faker = new Faker(new Locale("ru"));

    public static String generateCity() {
        List cities = Arrays.asList("Москва", "Магадан", "Самара", "Майкоп", "Кострома", "Махачкала", "Великий Новгород", "Саратов", "Севастополь", "Симферополь", "Краснодар", "Красноярск", "Псков", "владивосток", "Смоленск", "Ставрополь", "Астрахань", "Тула", "Тамбов");
        Random randomizer = new Random();
        Object city = cities.get(randomizer.nextInt(cities.size()));
        return (String) city;
    }

    public static String generateDate() {
        int addDaysL = faker.number().numberBetween(3, 10_000);
        long addDays = addDaysL;
        String date = LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
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

