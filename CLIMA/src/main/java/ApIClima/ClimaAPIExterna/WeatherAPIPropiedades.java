package ApIClima.ClimaAPIExterna;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeatherAPIPropiedades implements PropiedadesAPI{
    @Value("${weather.api.base-url:https://api.weatherapi.com/v1}")
    private String baseUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }
}
