import dto.MeasurementDTO;
import dto.MeasurementResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DrawChart {
    public static void main(String[] args) {
        List<Double> temperature = getTemperatureFromServer();
        drawChart(temperature);
    }

    private static List<Double> getTemperatureFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8082/measurements";

        MeasurementResponse jsonResponse = restTemplate.getForObject(url, MeasurementResponse.class);

        if (jsonResponse == null || jsonResponse.getMeasurements() == null)
            return Collections.emptyList();

        return jsonResponse.getMeasurements().stream().map(MeasurementDTO::getTemperature).collect(Collectors.toList());
    }

    private static void drawChart(List<Double> temperature) {
        double[] xData = IntStream.range(0, temperature.size()).asDoubleStream().toArray();
        double[] yData = temperature.stream().mapToDouble(x -> x).toArray();

        XYChart chart = QuickChart.getChart("Температура", "X", "Y", "temperature", xData, yData);

        new SwingWrapper<>(chart).displayChart();
    }
}
