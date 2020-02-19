package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

public class CachingForecastClient {

    private final ForecasterClient delegate;

    public CachingForecastClient(ForecasterClient delegate) {
            this.delegate=delegate;
    }

    public  Forecast forecastFor(Region region, Day day) {

        Forecast cached = delegate.forecastFor(region, day);
        return cached;
    }
}
