package demoqa.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        int index = getRandomInt(0, genders.length - 1);

        return genders[index];
    }
}
