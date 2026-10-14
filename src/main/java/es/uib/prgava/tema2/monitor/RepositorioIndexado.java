// RepositorioIndexado.java
package es.uib.prgava.tema2.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.uib.prgava.tema1.monitor.RepositorioResultados;
import es.uib.prgava.tema1.monitor.Resultado;
import es.uib.prgava.tema1.monitor.Servicio;

/**
 * Repositorio en memoria con un índice por servicio.
 *
 * <p>Misma interfaz que {@code RepositorioEnMemoria} del Tema 1 y mismos resultados; lo único que
 * cambia es que {@code historial} no recorre los resultados de los demás servicios.
 */
public final class RepositorioIndexado implements RepositorioResultados {

    private final Map<Servicio, List<Resultado>> porServicio = new HashMap<>();

    /** Coste O(1) esperado. */
    @Override
    public void guardar(Resultado resultado) {
        porServicio.computeIfAbsent(resultado.servicio(), s -> new ArrayList<>()).add(resultado);
    }

    /** Coste O(1) esperado para encontrar la lista, más O(k) para copiarla. */
    @Override
    public List<Resultado> historial(Servicio servicio) {
        return List.copyOf(porServicio.getOrDefault(servicio, List.of()));
    }

    /** Coste O(s): las claves ya son exactamente los servicios vistos. */
    @Override
    public Set<Servicio> servicios() {
        return Set.copyOf(porServicio.keySet());
    }
}
