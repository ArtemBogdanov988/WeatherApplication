import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeatherClient {
    public static void main(String[] args) {
        final String sensorName = "Test sensor 001";
        final String location = "Moscow";

        addWeatherSensor(sensorName, location);

        Random random = new Random();

        double minTemperature = 0;
        double maxTemperature = 45.0;
        for (int i = 0; i < 29; i++) {
            System.out.println(i);
            sendMeasurement(random.nextDouble() * maxTemperature, random.nextBoolean(), sensorName);
        }
    }

    private static void addWeatherSensor(String sensorName, String location) {
        final String url = "http://localhost:8082/weather_sensors/add";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("sensorName", sensorName);
        jsonData.put("location", location);

        postRequestWithJSONData(url, jsonData);
    }

    private static void sendMeasurement(double temperature, boolean raining, String sensorName) {
        final String url = "http://localhost:8082/measurements/registration";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("temperature", temperature);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("sensorName", sensorName));

        postRequestWithJSONData(url, jsonData);
    }

    private static void postRequestWithJSONData(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);

            System.out.println("Измерение отправлено на сервер");
        } catch (HttpClientErrorException e) {
            System.out.println("ОШИБКА!");
            System.out.println(e.getMessage());
        }
    }
}