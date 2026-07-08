package ApIClima.ClimaAPIExterna;

import ApIClima.DTO.BuscadorDeClimaDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Component
public class BuscadorDeClima implements BuscadorClima {

    private final RestTemplate restTemplate;
    private final PropiedadesAPI propiedades;

    public BuscadorDeClima(RestTemplate restTemplate, PropiedadesAPI propiedades) {
        this.restTemplate = restTemplate;
        this.propiedades = propiedades;
    }

    public BuscadorDeClimaDTO buscarClimaActual(String ubicacion) {
        URI uri = UriComponentsBuilder.fromUriString(propiedades.getBaseUrl())
                .path("/current.json")
                .queryParam("key", propiedades.getApiKey())
                .queryParam("q", ubicacion)
                .build()
                .toUri();

        BuscadorDeClimaDTO cuerpo = restTemplate.getForObject(uri, BuscadorDeClimaDTO.class);

        return cuerpo;
    }
}