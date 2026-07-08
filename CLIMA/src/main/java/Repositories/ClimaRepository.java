package Repositories;

import Dominio.Clima;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClimaRepository implements ClimaRepo{

    private final List<Clima> historial = new ArrayList<>();
    private int ultimoId = 0;

    public void guardar(Clima clima) {
        clima.setId(++ultimoId);
        historial.add(clima);
    }

    public List<Clima> obtenerTodos() {
        return historial;
    }

    public List<Clima> obtenerPosterioresA(int id) {
        return historial.stream()
                .filter(clima -> clima.getId() > id)
                .toList();
    }

    public Optional<Clima> obtenerUltimo() {
        if (historial.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(historial.get(historial.size() - 1));
    }
}
