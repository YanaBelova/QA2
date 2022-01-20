package ru.aviasales.utils;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

public class RandomUtils {
    public static String randomString(int lengthOfRandomString){
        RandomStringGenerator randomStringGenerator =
                new RandomStringGenerator.Builder()
                        .withinRange('0', 'z')
                        .filteredBy(CharacterPredicates.LETTERS)
                        .build();
        return randomStringGenerator.generate(lengthOfRandomString);
    }

    public static String checkForIdenticalString(int lengthOfNewString, String string){
        String secondLetter = randomString(lengthOfNewString);
        while (secondLetter.equals(string)){
            secondLetter=randomString(lengthOfNewString);
        }
        return secondLetter;
    }
}
