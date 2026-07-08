package Notificaciones;

import Dominio.Clima;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class NotificadorPorMail implements MedioNotificacion {

    private static final Logger logger = LoggerFactory.getLogger(NotificadorPorMail.class);
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final JavaMailSender mailSender;

    public NotificadorPorMail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviar(Clima clima, String destinatario) {
        logger.info("Enviando mail de alerta a {} para clima id={}", destinatario, clima.getId());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject("Alerta Climática Crítica - " + clima.getUbicacion());
        message.setText(construirDetalle(clima));

        mailSender.send(message);
        logger.info("Mail de alerta enviado a {} para clima id={}", destinatario, clima.getId());
    }

    private String construirDetalle(Clima clima) {
        return "Se detectó una condición climática crítica.\n\n" +
                "Ubicación: " + clima.getUbicacion() + ", " + clima.getRegion() + ", " + clima.getPais() + "\n" +
                "Fecha de registro: " + clima.getFecha().format(FORMATO_FECHA) + "\n" +
                "Última actualización (API): " + clima.getFechaActualizacionApi() + "\n\n" +
                "Temperatura: " + clima.getTemperatura() + "°C\n" +
                "Sensación térmica: " + clima.getSensacionTermica() + "°C\n" +
                "Humedad: " + clima.getHumedad() + "%\n" +
                "Condición: " + clima.getCondicion() + "\n" +
                "Viento: " + clima.getVientoKph() + " km/h\n" +
                "Precipitación: " + clima.getPrecipitacionMm() + " mm\n" +
                "Nubosidad: " + clima.getNubosidad() + "%\n" +
                "Índice UV: " + clima.getUv();
    }
}
