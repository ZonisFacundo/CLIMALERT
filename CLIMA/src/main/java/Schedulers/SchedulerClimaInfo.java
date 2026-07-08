package Schedulers;

import ApIClima.ClimaAPIExterna.BuscadorClima;
import Dominio.Clima;
import Repositories.ClimaRepo;
import ApIClima.DTO.BuscadorDeClimaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static ApIClima.DTO.AuxiliaresDTO.toEntity;

@Component
public class SchedulerClimaInfo {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerClimaInfo.class);

    private final String ubicacion;
    private final BuscadorClima obtenedorClima;
    private final ClimaRepo climaRepo;

    public SchedulerClimaInfo(BuscadorClima obtenedorClima, ClimaRepo repo,
                              @Value("${clima.ubicacion:CABA}") String ubicacion){
        this.obtenedorClima = obtenedorClima;
        this.climaRepo = repo;
        this.ubicacion = ubicacion;
    }

    @Scheduled(fixedRateString = "${clima.fetch-rate-ms:300000}", initialDelayString = "${clima.fetch-initial-delay-ms:0}")
    public void obtenerClima(){
        try {
            logger.info("Buscando clima actual para ubicacion={}", ubicacion);
            BuscadorDeClimaDTO climaActual = this.obtenedorClima.buscarClimaActual(ubicacion);
            Clima clima = toEntity(climaActual);
            this.climaRepo.guardar(clima);
            logger.info("Clima guardado id={} ubicacion={} temperatura={} humedad={} condicion={}",
                    clima.getId(),
                    clima.getUbicacion(),
                    clima.getTemperatura(),
                    clima.getHumedad(),
                    clima.getCondicion());
        } catch (RuntimeException e) {
            logger.error("No se pudo obtener o guardar el clima para ubicacion={}", ubicacion, e);
        }
    }
}
