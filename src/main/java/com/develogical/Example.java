package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class Example {
    public static void main(String[] args) {
        // This is just an example of using the 3rd party API - delete this class before submission.
        Forecaster forecaster = new Forecaster();



        Forecast forecast = forecaster.forecastFor(Region.LONDON, Day.MONDAY);

        printOutlook(forecast, "London", "Monday");


    }

    private static void printOutlook(Forecast forecast, String Region, String Day) {
        System.out.println(Region + " outlook: " + forecast.summary());
        System.out.println(Region + " temperature: " + forecast.temperature());
    }
}
