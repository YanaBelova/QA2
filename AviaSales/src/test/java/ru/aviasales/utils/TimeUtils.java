package ru.aviasales.utils;

import java.util.ArrayList;
import java.util.List;

public class TimeUtils {
    private static final int numberOfHoursInADay = 24;
    private static final int numberOfMinutesPerHour = 60;

    public static ArrayList<Integer> convertToMinutes(List<String> timeList){
        String delimeter = ":";
        int differenceBetweenTheLengthOfTheArrayAndTheIndexOfTheMinutesValue = 1;
        int differenceBetweenTheLengthOfTheArrayAndTheIndexOfTheHoursValue = 2;
        int differenceBetweenTheLengthOfTheArrayAndTheIndexOfTheDaysValue = 3;

        ArrayList<Integer> listOfFlightDurationInMinutes = new ArrayList<>();
        String[] arrayForDividingTimeIntoMinutesHoursAndDays;
        for (String time : timeList){
            int timeInMinutes = 0;
            arrayForDividingTimeIntoMinutesHoursAndDays = time.split(delimeter);
            for(int i = arrayForDividingTimeIntoMinutesHoursAndDays.length-1; i >= 0; i--) {
                if(i == arrayForDividingTimeIntoMinutesHoursAndDays.length-differenceBetweenTheLengthOfTheArrayAndTheIndexOfTheMinutesValue){
                    timeInMinutes += Integer.parseInt(arrayForDividingTimeIntoMinutesHoursAndDays[i]);}
                if(i == arrayForDividingTimeIntoMinutesHoursAndDays.length-differenceBetweenTheLengthOfTheArrayAndTheIndexOfTheHoursValue){
                    timeInMinutes += Integer.parseInt(arrayForDividingTimeIntoMinutesHoursAndDays[i])*numberOfMinutesPerHour;}
                if(i== arrayForDividingTimeIntoMinutesHoursAndDays.length-differenceBetweenTheLengthOfTheArrayAndTheIndexOfTheDaysValue){
                    timeInMinutes += Integer.parseInt(arrayForDividingTimeIntoMinutesHoursAndDays[i])*numberOfMinutesPerHour*numberOfHoursInADay;
                }
            }
            listOfFlightDurationInMinutes.add(timeInMinutes);
        }
        return listOfFlightDurationInMinutes;
    }
}
