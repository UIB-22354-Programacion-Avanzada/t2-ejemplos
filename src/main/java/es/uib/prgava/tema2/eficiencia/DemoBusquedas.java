// DemoBusquedas.java
package es.uib.prgava.tema2.eficiencia;

import java.util.Arrays;

/** Compara el trabajo de las dos búsquedas y muestra qué devuelve la de la biblioteca. */
public final class DemoBusquedas {

    /** Un array ordenado con los n primeros pares: 0, 2, 4, ... */
    private static int[] pares(int n) {
        var datos = new int[n];
        for (int i = 0; i < n; i++) {
            datos[i] = 2 * i;
        }
        return datos;
    }

    public static void main(String[] args) {
        System.out.println("--- cinco puertos, para leer los numeros a mano");
        int[] puertos = {22, 25, 80, 443, 8080};
        System.out.println("   posicion de 8080 = " + Busquedas.lineal(puertos, 8080));
        System.out.println("   posicion de 8080 = " + Busquedas.binaria(puertos, 8080));
        System.out.println("   posicion de 1024 = " + Busquedas.lineal(puertos, 1024));
        System.out.println("   posicion de 1024 = " + Busquedas.binaria(puertos, 1024));

        System.out.println();
        System.out.println("--- un millon de elementos: el elemento no esta");
        var muchos = pares(1 << 20);
        System.out.println("   posicion = " + Busquedas.lineal(muchos, -1));
        System.out.println("   posicion = " + Busquedas.binaria(muchos, -1));

        System.out.println();
        System.out.println("--- lo que devuelve la biblioteca");
        System.out.println("   buscar 443  -> " + Arrays.binarySearch(puertos, 443));
        System.out.println("   buscar 1024 -> " + Arrays.binarySearch(puertos, 1024));
        int posicion = Arrays.binarySearch(puertos, 1024);
        if (posicion < 0) {
            System.out.println("   1024 iria en la posicion " + (-posicion - 1));
        }
        System.out.println("   buscar 10   -> " + Arrays.binarySearch(puertos, 10));
        System.out.println("   10 iria en la posicion " + (-Arrays.binarySearch(puertos, 10) - 1));
    }
}
