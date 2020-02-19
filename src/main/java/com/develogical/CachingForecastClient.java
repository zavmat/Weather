package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

import java.util.HashMap;

public class CachingForecastClient {
    private final ForecasterClient delegate;
    private Forecast cachedItem;
    HashMap<Region, Forecast> localstorage = new HashMap<Region, Forecast>();
    public CachingForecastClient(ForecasterClient delegate) {
        this.delegate = delegate;
    }

    public Forecast forecastFor(Region region, Day day) {
        Forecast result=localstorage.get(region);
        if (result != null) {
            return result;
        }

        result = delegate.forecastFor(region, day);
        localstorage.put(region,result);
        return result;
    }
}

