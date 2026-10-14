// DemoConteos.java
package es.uib.prgava.tema2.eficiencia;

/** Ejecuta los tres recorridos de {@link Conteos} y deja que cada uno informe de su trabajo. */
public final class DemoConteos {

    private static final int N = 2_000;

    /** Un array de n enteros distintos: el peor caso para {@code hayRepetido}. */
    private static int[] sinRepetidos(int n) {
        var datos = new int[n];
        for (int i = 0; i < n; i++) {
            datos[i] = i;
        }
        return datos;
    }

    public static void main(String[] args) {
        System.out.println("--- un array pequeno, para leer los numeros a mano");
        var pequeno = new int[] {10, 20, 30};
        System.out.println("   total = " + Conteos.suma(pequeno));
        System.out.println("   total + maximo = " + Conteos.sumaYMaximo(pequeno));
        System.out.println("   hay repetido = " + Conteos.hayRepetido(pequeno));

        System.out.println();
        System.out.println("--- tres arrays del mismo tamano y distinto contenido");
        var sinRepetido = sinRepetidos(N);
        var conRepetidoAlPrincipio = sinRepetidos(N);
        conRepetidoAlPrincipio[1] = conRepetidoAlPrincipio[0];
        var conRepetidoEnMedio = sinRepetidos(N);
        conRepetidoEnMedio[N / 2] = conRepetidoEnMedio[N / 2 - 1];

        System.out.println("   hay repetido = " + Conteos.hayRepetido(sinRepetido));
        System.out.println("   hay repetido = " + Conteos.hayRepetido(conRepetidoEnMedio));
        System.out.println("   hay repetido = " + Conteos.hayRepetido(conRepetidoAlPrincipio));
    }
}
