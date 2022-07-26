package dto;

public class MeasurementDTO {
    private Double temperature;

    private Boolean raining;

    private WeatherSensorDTO sensor;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public WeatherSensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(WeatherSensorDTO sensor) {
        this.sensor = sensor;
    }
}