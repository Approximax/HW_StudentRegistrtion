package demoqa.utils;

import com.github.javafaker.Faker;

public class RandomUtils {

    Faker faker = new Faker();

    public String getRandomGender() {
        return faker.options().option(
                "Male",
                "Female",
                "Other"
        );
    }

    public String getRandomState() {
        return faker.options().option(
                "NCR",
                "Uttar Pradesh",
                "Haryana",
                "Rajasthan"
        );
    }

    public String getRandomCity(String state) {
        switch (state) {
            case "NCR": return faker.options().option("Delhi","Gurgaon","Noida");
            case "Uttar Pradesh": return faker.options().option("Agra","Lucknow","Merrut");
            case "Haryana": return faker.options().option("Karnal","Panipat");
            case "Rajasthan": return  faker.options().option("Jaipur","Jaiselmer");
        }
        return state;
    }

    public String getRandomHobby() {
        return faker.options().option("Music", "Sports", "Reading");
    }

    public String getRandomDay() {
        return String.valueOf(faker.random().nextInt(1,28));
    }

    public String getRandomMonth() {
        return faker.options().option(
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        );
    }

    public String getRandomYear() {
        return String.valueOf(faker.random().nextInt(1970, 2024));
    }

    public  String getRandomSubject() {
        return faker.options().option(
                "Maths",
                "Arts",
                "Social Studies",
                "Accounting",
                "Biology"
        );
    }
}