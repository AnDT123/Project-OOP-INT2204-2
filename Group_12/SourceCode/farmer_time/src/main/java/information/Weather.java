package information;
public class Weather {
    private String city;

    private double temperature;

    private String description;

    private long humidity;

    private double windSpeed;

    private double feelsLike;

    private String iconImage;

    public Weather(String city, double temperature, String description, Long humidity, double windSpeed, double fellsLike, String iconImage) {
        this.city = city;
        this.temperature = temperature;
        this.description = description;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.feelsLike = fellsLike;
        this.iconImage = iconImage;
    }

    public String getCity() {
        return this.city;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public String getDescription() {
        return this.description;
    }

    public long getHumidity() {
        return this.humidity;
    }

    public double getWindSpeed() {
        return this.windSpeed;
    }
    
    public double getFellsLike() {
        return this.feelsLike;
    }

    public String getIconImage() {
        return this.iconImage;
    }
}