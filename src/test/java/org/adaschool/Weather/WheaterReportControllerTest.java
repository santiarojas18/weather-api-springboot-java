package org.adaschool.Weather;
import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class WheaterReportControllerTest {
    final String BASE_URL = "/v1/api/weather-report";
    @MockBean
    private WeatherReportService weatherReportService;
    @Autowired
    private WeatherReportController controller;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testFindTemperature() throws Exception {
        WeatherReport report = new WeatherReport();
        report.setTemperature(288.1);
        report.setHumidity(62.0);
        when(weatherReportService.getWeatherReport(37.8267, -122.4233)).thenReturn(report);

        mockMvc.perform(get(BASE_URL + "?latitude=37.8267&longitude=-122.4233"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature", is(288.1)))
                .andExpect(jsonPath("$.humidity", is(62.0)));

        verify(weatherReportService, times(1)).getWeatherReport(37.8267, -122.4233);
    }
}
