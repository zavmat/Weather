package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

public class CachingForecastClient {
    private final ForecasterClient delegate;
    private Forecast cachedItem;

    public CachingForecastClient(ForecasterClient delegate) {
        this.delegate = delegate;
    }

    public Forecast forecastFor(Region region, Day day) {
        if (cachedItem != null) {
            return cachedItem;
        }
        Forecast cached = delegate.forecastFor(region, day);
        this.cachedItem = cached;
        return cached;
    }
}

