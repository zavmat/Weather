package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

import java.util.HashMap;
import java.util.Map;

public class CachingForecastClient {
    private final ForecasterClient delegate;
    HashMap<Region, HashMap<Day, Forecast>> localStorage = new HashMap<Region, HashMap<Day, Forecast>> ();
    public CachingForecastClient(ForecasterClient delegate) {
        this.delegate = delegate;
    }

    public Forecast forecastFor(Region region, Day day) {
        Forecast result;
        HashMap<Day, Forecast> days= localStorage.get(region);
        if (days != null) {
            result = days.get(day);
            if(result!=null){
                return result;
            }

        }

        result = delegate.forecastFor(region, day);
        HashMap<Day,Forecast> element = new HashMap<Day,Forecast>();
        element.put(day,result);
        localStorage.put(region, element);
        return result;
    }
}

