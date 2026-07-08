package ApIClima.ClimaAPIExterna;

import ApIClima.DTO.BuscadorDeClimaDTO;

public interface BuscadorClima {
           BuscadorDeClimaDTO buscarClimaActual(String ubicacion);
}
