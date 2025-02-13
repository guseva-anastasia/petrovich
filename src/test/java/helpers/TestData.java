package helpers;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {


    Faker faker = new Faker(new Locale("en"));

    public
    String productName = faker.options().option("Ламинат", "Клей", "Обои", "Перфоратор");

}