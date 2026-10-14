# t2-ejemplos — Código del Tema 2: Algoritmos, Complejidad y Estructuras de Datos

Todos los listados de código del
[Tema 2](https://uib-22354-programacion-avanzada.github.io/website/es/contenido/tema2.html) de
*Programación Avanzada (22354)*, Grado en Ingeniería Telemática, UIB-EPS, reunidos en un proyecto
que compila y se ejecuta.

Es un proyecto **Maven** para **Java 25** con pruebas en **JUnit 5**, preparado para **GitHub
Codespaces**. A diferencia de `t2-ejercicios`, aquí **no hay nada que resolver**: el código está
completo y la gracia es ejecutarlo, modificarlo y ver qué cambia.

## Cómo ejecutarlo

**En el navegador, con Codespaces.** **Code** → **Codespaces** → **Create codespace on main**. Se
abre VS Code con el JDK 25 y Maven ya puestos.

**En tu ordenador.** Clona el repositorio y abre el proyecto por su `pom.xml`: en IntelliJ,
**File → Open** y selecciona el `pom.xml` (elige *Open as Project*); en VS Code, abre la carpeta y
deja que la extensión de Java la importe. Necesitas un **JDK 25**.

```bash
mvn -q compile                                   # compilar
mvn test                                         # las pruebas
mvn -q compile exec:java -Dexec.mainClass=es.uib.prgava.tema2.eficiencia.DemoConteos
```

Los dos programas con `main` también se lanzan con el botón de ejecutar del IDE, que es más cómodo.

## Qué hay aquí

```text
t2-ejemplos/
├── pom.xml
└── src/
    ├── main/java/es/uib/prgava/
    │   ├── tema1/monitor/        ← diez clases del Tema 1, copiadas (ver más abajo)
    │   └── tema2/
    │       ├── eficiencia/       ← sección 2.1: contar operaciones, buscar
    │       └── monitor/          ← secciones 2.2 y 2.3: el monitor a escala
    └── test/java/es/uib/prgava/tema2/monitor/
```

### `tema2.eficiencia` — sección 2.1

| Clase | Qué es |
|---|---|
| `Recuento` | el formato único con el que todas las clases informan de lo que han contado |
| `Conteos` | tres recorridos que cuentan las iteraciones de sus bucles y las imprimen |
| `DemoConteos` | los ejecuta; enseña que dos entradas del mismo tamaño cuestan 1.999.000 y 1 |
| `Busquedas` | búsqueda lineal y binaria, contando también |
| `DemoBusquedas` | 1.048.576 comparaciones frente a 20, y qué devuelve `Arrays.binarySearch` |

### `tema2.monitor` — secciones 2.2 y 2.3

| Clase | Qué es |
|---|---|
| `VentanaDeslizante` | los últimos `n` resultados de un servicio, sobre un `ArrayDeque` |
| `RepositorioIndexado` | el repositorio con un `HashMap` por servicio |
| `RepositorioConVentana` | índice completo para los informes y ventana para la política |
| `IndiceTemporal` | resultados por instante en un `TreeMap`, con consultas de intervalo |
| `ColaDeAlertas` | alertas pendientes en una `PriorityQueue`, por gravedad y antigüedad |
| `CosteHistorialTest` | la prueba que defiende la clase de crecimiento contando `equals` |

## Las clases del Tema 1 que vienen copiadas

Las cinco clases de `tema2.monitor` son las piezas nuevas del ejemplo unificador, y usan tipos que
se construyeron en el Tema 1: `Servicio` con sus tres implementaciones, `Estado`, `Resultado`,
`Alerta` y las tres interfaces del repositorio. Para que este proyecto **se clone y funcione sin
configurar nada**, esas diez clases están copiadas aquí en lugar de declararse como dependencia de
[`t1-ejemplos`](https://github.com/UIB-22354-Programacion-Avanzada/t1-ejemplos).

Conviene saber lo que eso implica: **esas diez clases son una copia, y su original está en
`t1-ejemplos`**. Si encuentras algo que corregir en ellas, el sitio donde corregirlo es el Tema 1.
Aquí están para que el Tema 2 se pueda ejecutar, no para evolucionar.

## Lo que el código no dice

Los recuentos que estas clases imprimen son **iteraciones del cuerpo** de los bucles, no operaciones
elementales: cada iteración son unas cuantas —el acceso al array, la suma, la comparación de la
condición, el incremento del índice—, y las dos cuentas se diferencian en un factor constante. El
tema lo explica en 2.1.1, y el `javadoc` de `Recuento` lo recuerda.

Y las mediciones de tiempo que aparecen en el tema —la tabla del coste cuadrático de 2.1.6 y la de
las tres versiones del repositorio de 2.3.3— **no se obtienen de este proyecto**: se midieron con un
programa de prueba que no forma parte de los listados. Si quieres reproducirlas, el tema dice cómo
(20 servicios, política de tres fallos consecutivos, ventana de diez, el mínimo de tres repeticiones
después de calentar la máquina virtual).

## Integridad académica

El uso de asistentes de IA se rige por las **condiciones de uso de la IA** de la
[guía docente](https://uib-22354-programacion-avanzada.github.io/website/es/informaciones/guia-docente.html).

## Licencia

Código bajo licencia [MIT](LICENSE) — copyright © 2026 Alejandro Mesejo. Los enunciados y el resto
del material docente están en el
[sitio web de la asignatura](https://uib-22354-programacion-avanzada.github.io/website/), bajo
licencia [CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en).
