// CosteHistorialTest.java
package es.uib.prgava.tema2.monitor;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import es.uib.prgava.tema1.monitor.Resultado;
import es.uib.prgava.tema1.monitor.Servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CosteHistorialTest {

    /** Un servicio que cuenta cuántas veces se le pregunta la identidad. */
    private record ServicioEspia(String nombreReal, AtomicInteger comparaciones)
            implements Servicio {

        @Override
        public String nombre() {
            return nombreReal;
        }

        @Override
        public boolean equals(Object otro) {
            comparaciones.incrementAndGet();
            return otro instanceof ServicioEspia ese && nombreReal.equals(ese.nombreReal);
        }

        @Override
        public int hashCode() {
            return nombreReal.hashCode();
        }
    }

    @Test
    void laVentanaNoCreceConElNumeroDeRondas() {
        var repositorio = new RepositorioConVentana(10);
        var servicio = Servicio.desde("tcp:web01:80");

        for (int i = 0; i < 1_000; i++) {
            repositorio.guardar(Resultado.activo(servicio, Duration.ofMillis(10)));
        }

        assertEquals(10, repositorio.ultimos(servicio).size(),
                "la ventana debe quedarse en su capacidad, no crecer con las rondas");
        assertEquals(1_000, repositorio.historial(servicio).size(),
                "el historial completo sí conserva todo");
    }

    @Test
    void consultarLaVentanaNoDependeDeLosDemasServicios() {
        var contador = new AtomicInteger();
        var vigilado = new ServicioEspia("web01", contador);
        var repositorio = new RepositorioConVentana(10);

        for (int i = 0; i < 200; i++) {
            repositorio.guardar(Resultado.activo(new ServicioEspia("otro" + i, contador),
                    Duration.ofMillis(5)));
        }
        repositorio.guardar(Resultado.activo(vigilado, Duration.ofMillis(5)));

        contador.set(0);
        repositorio.ultimos(vigilado);

        assertTrue(contador.get() < 10,
                "consultar un servicio no debe comparar con los 200 demás; fueron "
                        + contador.get());
    }
}
