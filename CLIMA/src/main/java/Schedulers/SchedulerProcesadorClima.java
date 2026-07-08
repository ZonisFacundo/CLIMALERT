package Schedulers;

import Dominio.Clima;
import Notificaciones.GestorDeAlertas;
import Repositories.ClimaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedulerProcesadorClima {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerProcesadorClima.class);

    private final ClimaRepo repositorio;
    private final GestorDeAlertas gestorDeAlertas;
    private int ultimoIdProcesado = 0;

    public SchedulerProcesadorClima(ClimaRepo repositorio, GestorDeAlertas gestorDeAlertas) {
        this.repositorio = repositorio;
        this.gestorDeAlertas = gestorDeAlertas;
    }

    @Scheduled(fixedRateString = "${clima.process-rate-ms:60000}", initialDelayString = "${clima.process-initial-delay-ms:5000}")
    public void procesarAlertas() {
        List<Clima> climasNuevos = repositorio.obtenerPosterioresA(ultimoIdProcesado);
        if (climasNuevos.isEmpty()) {
            logger.info("No hay registros nuevos para procesar. ultimoIdProcesado={}", ultimoIdProcesado);
            return;
        }

        logger.info("Procesando {} registros nuevos desde ultimoIdProcesado={}", climasNuevos.size(), ultimoIdProcesado);
        climasNuevos.forEach(clima -> {
            procesarClima(clima);
            ultimoIdProcesado = clima.getId();
        });
    }

    private void procesarClima(Clima clima) {
        if (clima.getTemperatura() > 35.0 && clima.getHumedad() > 60) {
            logger.warn("Alerta critica detectada id={} temperatura={} humedad={}",
                    clima.getId(),
                    clima.getTemperatura(),
                    clima.getHumedad());
            try {
                gestorDeAlertas.despacharAlerta(clima);
                logger.info("Alerta despachada correctamente para clima id={}", clima.getId());
            } catch (RuntimeException e) {
                logger.error("No se pudo despachar la alerta para clima id={}", clima.getId(), e);
            }
        } else {
            logger.info("Clima id={} revisado sin alerta. temperatura={} humedad={}",
                    clima.getId(),
                    clima.getTemperatura(),
                    clima.getHumedad());
        }
    }
}
