package ApIClima.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuscadorDeClimaDTO {
    @JsonProperty("location")
    private Location location;

    @JsonProperty("current")
    private Current current;

    public BuscadorDeClimaDTO() {
    }

    public BuscadorDeClimaDTO(double temperatura, int humedad) {
        this.current = new Current();
        this.current.temperatura = temperatura;
        this.current.humedad = humedad;
    }

    public double getTemperatura() {
        if (current == null) {
            return 0.0;
        }
        return current.temperatura;
    }

    public int getHumedad() {
        if (current == null) {
            return 0;
        }
        return current.humedad;
    }

    public String getUbicacion() {
        if (location == null) {
            return null;
        }
        return location.nombre;
    }

    public String getRegion() {
        if (location == null) {
            return null;
        }
        return location.region;
    }

    public String getPais() {
        if (location == null) {
            return null;
        }
        return location.pais;
    }

    public String getCondicion() {
        if (current == null || current.condicion == null) {
            return null;
        }
        return current.condicion.texto;
    }

    public double getVientoKph() {
        if (current == null) {
            return 0.0;
        }
        return current.vientoKph;
    }

    public double getPrecipitacionMm() {
        if (current == null) {
            return 0.0;
        }
        return current.precipitacionMm;
    }

    public int getNubosidad() {
        if (current == null) {
            return 0;
        }
        return current.nubosidad;
    }

    public double getSensacionTermica() {
        if (current == null) {
            return 0.0;
        }
        return current.sensacionTermica;
    }

    public double getUv() {
        if (current == null) {
            return 0.0;
        }
        return current.uv;
    }

    public String getFechaActualizacionApi() {
        if (current == null) {
            return null;
        }
        return current.fechaActualizacion;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        @JsonProperty("name")
        private String nombre;

        @JsonProperty("region")
        private String region;

        @JsonProperty("country")
        private String pais;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Current {
        @JsonProperty("temp_c")
        private double temperatura;

        @JsonProperty("humidity")
        private int humedad;

        @JsonProperty("condition")
        private Condition condicion;

        @JsonProperty("wind_kph")
        private double vientoKph;

        @JsonProperty("precip_mm")
        private double precipitacionMm;

        @JsonProperty("cloud")
        private int nubosidad;

        @JsonProperty("feelslike_c")
        private double sensacionTermica;

        @JsonProperty("uv")
        private double uv;

        @JsonProperty("last_updated")
        private String fechaActualizacion;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Condition {
        @JsonProperty("text")
        private String texto;
    }
}
