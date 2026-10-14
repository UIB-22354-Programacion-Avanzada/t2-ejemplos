// Conteos.java
package es.uib.prgava.tema2.eficiencia;

/**
 * Tres recorridos que cuentan su propio trabajo.
 *
 * <p>Cada método lleva un contador de las <strong>iteraciones del cuerpo de sus 
 * bucles</strong> y lo anuncia con {@link Recuento} antes de devolver el resultado, junto con la 
 * fórmula que predice ese número. No cuenta todas las operaciones elementales —cada iteración 
 * son unas cuantas: el acceso al array, la suma, la comparación y el incremento—, 
 * sino las iteraciones, que es lo que cuenta el análisis del coste. Las dos cuentas 
 * se diferencian en un factor constante.
 *
 * <p>Imprimir desde una clase que calcula es mala práctica y contradice el 
 * principio de responsabilidad única del Tema 1. Se hace aquí a propósito, porque esta 
 * clase no es un componente reutilizable: su razón de ser es enseñar a contar, y el 
 * recuento es lo que hay que ver.
 */
public final class Conteos {

    private Conteos() { }

    /** Un recorrido: el cuerpo se ejecuta n veces. */
    public static long suma(int[] datos) {
        long operaciones = 0;
        long total = 0;
        for (int i = 0; i < datos.length; i++) {
            total += datos[i];
            operaciones++;
        }
        Recuento.anunciar("suma", datos.length, operaciones, "n");
        return total;
    }

    /** Dos bucles anidados: el cuerpo interno se ejecuta una vez por cada par de índices. */
    public static boolean hayRepetido(int[] datos) {
        long operaciones = 0;
        for (int i = 0; i < datos.length; i++) {
            for (int j = i + 1; j < datos.length; j++) {
                operaciones++;
                if (datos[i] == datos[j]) {
                    Recuento.anunciar("hayRepetido", datos.length, operaciones, "n(n-1)/2 como maximo");
                    return true;
                }
            }
        }
        Recuento.anunciar("hayRepetido", datos.length, operaciones, "n(n-1)/2");
        return false;
    }

    /** Dos bucles seguidos, no anidados: el cuerpo se ejecuta n + n veces. */
    public static long sumaYMaximo(int[] datos) {
        long operaciones = 0;
        long total = 0;
        for (int dato : datos) {
            total += dato;
            operaciones++;
        }
        int maximo = Integer.MIN_VALUE;
        for (int dato : datos) {
            maximo = Math.max(maximo, dato);
            operaciones++;
        }
        Recuento.anunciar("sumaYMaximo", datos.length, operaciones, "2n");
        return total + maximo;
    }
}
