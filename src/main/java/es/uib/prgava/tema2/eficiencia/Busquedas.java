// Busquedas.java
package es.uib.prgava.tema2.eficiencia;

/**
 * Búsqueda lineal y búsqueda binaria, cada una contando su propio trabajo.
 *
 * <p>Como en {@link Conteos}, el contador lleva las <strong>iteraciones del cuerpo</strong> del
 * bucle y se anuncia con {@link Recuento} antes de devolver el resultado. Aquí cada iteración es
 * una comparación con un elemento del array, así que lo que la columna llama operaciones son,
 * exactamente, comparaciones.
 */
public final class Busquedas {

    private Busquedas() { }

    /** Posición de {@code buscado} en {@code datos}, o −1. Coste O(n). */
    public static int lineal(int[] datos, int buscado) {
        long operaciones = 0;
        for (int i = 0; i < datos.length; i++) {
            operaciones++;
            if (datos[i] == buscado) {
                Recuento.anunciar("lineal", datos.length, operaciones, "n como maximo");
                return i;
            }
        }
        Recuento.anunciar("lineal", datos.length, operaciones, "n");
        return -1;
    }

    /**
     * Posición de {@code buscado} en {@code datos}, o −1. Coste O(log n).
     *
     * @param datos array <strong>ordenado</strong> de forma creciente
     */
    public static int binaria(int[] datos, int buscado) {
        long operaciones = 0;
        int izquierda = 0;
        int derecha = datos.length - 1;
        while (izquierda <= derecha) {
            operaciones++;
            int centro = izquierda + (derecha - izquierda) / 2;
            if (datos[centro] == buscado) {
                Recuento.anunciar("binaria", datos.length, operaciones, "log2(n) + 1 como maximo");
                return centro;
            }
            if (datos[centro] < buscado) {
                izquierda = centro + 1;
            } else {
                derecha = centro - 1;
            }
        }
        Recuento.anunciar("binaria", datos.length, operaciones, "log2(n) + 1");
        return -1;
    }
}
