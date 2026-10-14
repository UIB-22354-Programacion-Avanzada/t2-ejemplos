// Recuento.java
package es.uib.prgava.tema2.eficiencia;

/**
 * Formato único con el que las clases de esta sección informan de lo que han contado.
 *
 * <p>Existe para que todos los recuentos del tema se lean igual: el nombre del método, el tamaño de
 * la entrada, las operaciones contadas y la fórmula que predice ese número. Lo que se cuenta son
 * siempre <strong>iteraciones del cuerpo</strong> de los bucles, no operaciones elementales.
 */
public final class Recuento {

    private Recuento() { }

    public static void anunciar(String metodo, int n, long operaciones, String formula) {
        System.out.printf("%-12s n = %-9d operaciones = %-9d  (%s)%n",
                metodo, n, operaciones, formula);
    }
}
