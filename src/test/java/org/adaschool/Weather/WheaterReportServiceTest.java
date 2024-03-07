package org.adaschool.Weather;
import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class WheaterReportServiceTest {
    @Autowired
    private WeatherReportService service;

    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testConectionWithExternalWeatherService() {
        WeatherReport report = new WeatherReport();
        WeatherApiResponse response = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?lat=37.8267&lon=-122.4233&appid=3c4a981d4bea1ce1f212df4d9b755e60", WeatherApiResponse.class);
        report.setTemperature(response.getMain().getTemperature());
        report.setHumidity(response.getMain().getHumidity());
        assertEquals(report.getTemperature(), service.getWeatherReport(37.8267, -122.4233).getTemperature());
        assertEquals(report.getHumidity(), service.getWeatherReport(37.8267, -122.4233).getHumidity());

    }
}
