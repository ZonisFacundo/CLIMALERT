package Notificaciones;

import Dominio.Clima;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GestorDeAlertas {

    private static final Logger logger = LoggerFactory.getLogger(GestorDeAlertas.class);

    private final MedioNotificacion notificador;

    private final List<String> destinatarios = new ArrayList<>(List.of(
            "admin@clima.com",
            "emergencias@clima.com",
            "meteorologia@clima.com"
    ));

    public void agregarNotificador(String noti){
        destinatarios.add(noti);
    }
    public void quitarNotificador(String noti){
        destinatarios.remove(noti);
    }

    public GestorDeAlertas(MedioNotificacion notificador) {
        this.notificador = notificador;
    }

    public void despacharAlerta(Clima clima) {
        logger.info("Despachando alerta de clima id={} a {} destinatarios", clima.getId(), destinatarios.size());
        destinatarios.forEach(destinatario -> notificador.enviar(clima, destinatario));
    }
}
