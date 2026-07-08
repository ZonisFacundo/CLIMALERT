package Repositories;

import Dominio.Clima;

import java.util.List;
import java.util.Optional;

public interface ClimaRepo {

    void guardar(Clima clima);

    List<Clima> obtenerTodos();

    List<Clima> obtenerPosterioresA(int id);

    Optional<Clima> obtenerUltimo();
}
