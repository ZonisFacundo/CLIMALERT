package Dominio;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Clima {

    private int id;
    private LocalDateTime fecha;
    private final String ubicacion;
    private final String region;
    private final String pais;
    private final double temperatura;
    private final int humedad;
    private final String condicion;
    private final double vientoKph;
    private final double precipitacionMm;
    private final int nubosidad;
    private final double sensacionTermica;
    private final double uv;
    private final String fechaActualizacionApi;

    public Clima(double temperatura, int humedad) {
        this(null, null, null, temperatura, humedad, null, 0.0, 0.0, 0, 0.0, 0.0, null);
    }

    public Clima(String ubicacion, String region, String pais, double temperatura, int humedad,
                 String condicion, double vientoKph, double precipitacionMm, int nubosidad,
                 double sensacionTermica, double uv, String fechaActualizacionApi) {
        this.ubicacion = ubicacion;
        this.region = region;
        this.pais = pais;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.condicion = condicion;
        this.vientoKph = vientoKph;
        this.precipitacionMm = precipitacionMm;
        this.nubosidad = nubosidad;
        this.sensacionTermica = sensacionTermica;
        this.uv = uv;
        this.fechaActualizacionApi = fechaActualizacionApi;
        this.fecha = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getRegion() {
        return region;
    }

    public String getPais() {
        return pais;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public int getHumedad() {
        return humedad;
    }

    public String getCondicion() {
        return condicion;
    }

    public double getVientoKph() {
        return vientoKph;
    }

    public double getPrecipitacionMm() {
        return precipitacionMm;
    }

    public int getNubosidad() {
        return nubosidad;
    }

    public double getSensacionTermica() {
        return sensacionTermica;
    }

    public double getUv() {
        return uv;
    }

    public String getFechaActualizacionApi() {
        return fechaActualizacionApi;
    }

}
