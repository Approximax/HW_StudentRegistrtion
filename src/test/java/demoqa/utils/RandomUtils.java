package demoqa.utils;

import com.github.javafaker.Faker;

public class RandomUtils {

    Faker faker = new Faker();

//    public  int getRandomInt(int min, int max) {
//        return ThreadLocalRandom.current().nextInt(min, max + 1);
//    }

//    public static String getRandomGender() {
//        String[] genders = {"Male", "Female", "Other"};
//
//        int index = getRandomInt(0, genders.length - 1);
//
//        return genders[index];
//    }

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

//    public static String getRandomDay() {
//        return String.valueOf(random.nextInt(28));
//    }

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

//    2024-1900+1

//    public static String getRandomYear() {
//        return String.valueOf((1900 + random.nextInt(125)));
//    }

    public String getRandomYear() {
        return String.valueOf(faker.random().nextInt(1970, 2024));
    }
}