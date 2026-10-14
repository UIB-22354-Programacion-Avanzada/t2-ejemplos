// ColaDeAlertas.java
package es.uib.prgava.tema2.monitor;

import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

import es.uib.prgava.tema1.monitor.Alerta;
import es.uib.prgava.tema1.monitor.Estado;

/**
 * Alertas pendientes de enviar, ordenadas por gravedad y, a igual gravedad, por antigüedad.
 *
 * <p>Encolar y extraer la más grave cuestan O(log n); consultarla sin extraerla, O(1).
 */
public final class ColaDeAlertas {

    /** Primero los caídos, después los degradados; a igual estado, la más antigua. */
    private static final Comparator<Pendiente> POR_GRAVEDAD =
            Comparator.comparingInt((Pendiente p) -> gravedad(p.estado()))
                    .thenComparing(p -> p.alerta().instante());

    private record Pendiente(Alerta alerta, Estado estado) { }

    private final Queue<Pendiente> pendientes = new PriorityQueue<>(POR_GRAVEDAD);

    private static int gravedad(Estado estado) {
        return switch (estado) {
            case CAIDO -> 0;
            case DEGRADADO -> 1;
            case ACTIVO -> 2;
        };
    }

    public void encolar(Alerta alerta, Estado estado) {
        pendientes.offer(new Pendiente(alerta, estado));
    }

    /** Extrae la alerta más grave pendiente, si hay alguna. */
    public Optional<Alerta> siguiente() {
        var pendiente = pendientes.poll();
        return pendiente == null ? Optional.empty() : Optional.of(pendiente.alerta());
    }

    public int pendientes() {
        return pendientes.size();
    }
}
