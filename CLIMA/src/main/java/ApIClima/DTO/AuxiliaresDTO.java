package ApIClima.DTO;

import Dominio.Clima;

public class AuxiliaresDTO {

    public static Clima toEntity(BuscadorDeClimaDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO es NULL");
        }

        return new Clima(
                dto.getUbicacion(),
                dto.getRegion(),
                dto.getPais(),
                dto.getTemperatura(),
                dto.getHumedad(),
                dto.getCondicion(),
                dto.getVientoKph(),
                dto.getPrecipitacionMm(),
                dto.getNubosidad(),
                dto.getSensacionTermica(),
                dto.getUv(),
                dto.getFechaActualizacionApi()
        );
    }
}
