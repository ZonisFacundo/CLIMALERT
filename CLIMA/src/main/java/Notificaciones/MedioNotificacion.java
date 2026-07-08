package Notificaciones;

import Dominio.Clima;

public interface MedioNotificacion {
    void enviar(Clima clima, String destinatario);
}