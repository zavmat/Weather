package com.develogical;

import com.weather.Forecast;
import com.weather.Region;
import com.weather.Day;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

public class PutYourTestCodeInThisDirectoryTest {
    @Test
    public void TestQueryForForecast() throws Exception {
        ForecasterClient delegate = mock(ForecasterClient.class);
        when(delegate.forecastFor(Region.LONDON, Day.MONDAY)).thenReturn(new Forecast("cloudy", 25));
        CachingForecastClient client = new CachingForecastClient(delegate);
        Forecast newForecast = client.forecastFor(Region.LONDON, Day.MONDAY);
        assertThat(newForecast.summary(), equalTo("cloudy"));
        assertThat(newForecast.temperature(), equalTo(25));
    }

    @Test
    public void TestQueryWithCache() throws Exception {
        ForecasterClient delegate = mock(ForecasterClient.class);
        when(delegate.forecastFor(Region.LONDON, Day.MONDAY)).thenReturn(new Forecast("cloudy", 25));
        CachingForecastClient client = new CachingForecastClient(delegate);
        Forecast newForecast = client.forecastFor(Region.LONDON, Day.MONDAY);
        Forecast sameForecast = client.forecastFor(Region.LONDON, Day.MONDAY);
        verify(delegate, times(1)).forecastFor(ArgumentMatchers.<Region>any(), ArgumentMatchers.<Day>any());
    }

    @Test
    public void testCacheWithDifferentRegions() throws Exception {
        ForecasterClient delegate = mock(ForecasterClient.class);
        when(delegate.forecastFor(Region.LONDON, Day.MONDAY)).thenReturn(new Forecast("cloudy", 25));
        when(delegate.forecastFor(Region.BIRMINGHAM, Day.MONDAY)).thenReturn(new Forecast("sunny", 10));
        CachingForecastClient client = new CachingForecastClient(delegate);
        Forecast newForecast = client.forecastFor(Region.LONDON, Day.MONDAY);
        Forecast secondForecast = client.forecastFor(Region.BIRMINGHAM, Day.MONDAY);
        Forecast thirdForecast = client.forecastFor(Region.BIRMINGHAM, Day.MONDAY);
        verify(delegate, times(2)).forecastFor(ArgumentMatchers.<Region>any(), ArgumentMatchers.<Day>any());
        assertThat(thirdForecast.summary(), equalTo("sunny"));
        assertThat(thirdForecast.temperature(), equalTo(10));
    }

    @Test
    public void testCacheWithDifferentDays() throws Exception {
        ForecasterClient delegate = mock(ForecasterClient.class);
        when(delegate.forecastFor(Region.LONDON, Day.MONDAY)).thenReturn(new Forecast("cloudy", 25));
        when(delegate.forecastFor(Region.LONDON, Day.TUESDAY)).thenReturn(new Forecast("sunny", 10));
        CachingForecastClient client = new CachingForecastClient(delegate);
        Forecast newForecast = client.forecastFor(Region.LONDON, Day.MONDAY);
        Forecast secondForecast = client.forecastFor(Region.LONDON, Day.TUESDAY);
        Forecast thirdForecast = client.forecastFor(Region.LONDON, Day.TUESDAY);
        verify(delegate, times(2)).forecastFor(ArgumentMatchers.<Region>any(), ArgumentMatchers.<Day>any());
        assertThat(thirdForecast.summary(), equalTo("sunny"));
        assertThat(thirdForecast.temperature(), equalTo(10));
    }

}
