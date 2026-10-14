// IndiceTemporal.java
package es.uib.prgava.tema2.monitor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.TreeMap;

import es.uib.prgava.tema1.monitor.Resultado;

/**
 * Resultados de un servicio indexados por el instante de la comprobación.
 *
 * <p>Permite responder consultas de intervalo y de vecindad en tiempo logarítmico, que es lo que
 * una tabla <em>hash</em> no sabe hacer.
 */
public final class IndiceTemporal {

    private final NavigableMap<Instant, List<Resultado>> porInstante = new TreeMap<>();

    /** Coste O(log n). Varios resultados pueden compartir instante. */
    public void agregar(Resultado resultado) {
        porInstante.computeIfAbsent(resultado.instante(), i -> new ArrayList<>()).add(resultado);
    }

    /**
     * Resultados con instante en {@code [desde, hasta)}.
     *
     * <p>Coste O(log n) para situar el intervalo, más O(k) para recorrer sus k resultados: no
     * depende del tamaño total del índice.
     */
    public List<Resultado> entre(Instant desde, Instant hasta) {
        var encontrados = new ArrayList<Resultado>();
        for (var resultados : porInstante.subMap(desde, true, hasta, false).values()) {
            encontrados.addAll(resultados);
        }
        return List.copyOf(encontrados);
    }

    /** El último resultado anterior o igual a {@code momento}, si lo hay. Coste O(log n). */
    public Optional<Resultado> ultimoAntesDe(Instant momento) {
        var entrada = porInstante.floorEntry(momento);
        if (entrada == null) {
            return Optional.empty();
        }
        var resultados = entrada.getValue();
        return Optional.of(resultados.get(resultados.size() - 1));
    }

    public int cuantos() {
        return porInstante.size();
    }
}
