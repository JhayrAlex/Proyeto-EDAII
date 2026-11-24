package ec.edu.epn.eda2.util;

/** Textos teóricos mostrados en la interfaz. */
public class Contenido {

    public static final String EFICIENCIA = """
EFICIENCIA ALGORÍTMICA

Un mismo problema puede resolverse con distintos algoritmos, y cada uno tiene un costo computacional distinto (tiempo y memoria). Analizar la eficiencia permite elegir la mejor estrategia ANTES de implementarla.

Problema de estudio: suma de los números naturales de 1 hasta n.

Método 1 – Iterativo (for)
    suma = 0;  for (i = 1; i <= n; i++) suma += i;
    Realiza n sumas → O(n) en tiempo, O(1) en espacio.

Método 2 – Fórmula de Gauss
    suma = n · (n + 1) / 2
    Realiza 3 operaciones sin importar n → O(1) en tiempo, O(1) en espacio.

Característica         FOR              Gauss
Complejidad temporal   O(n)             O(1)
Complejidad espacial   O(1)             O(1)
Operaciones            Crecen con n     Cantidad constante
Resultado              Suma de 1 a n    Suma de 1 a n

Uso: ingresa n, elige el método y pulsa «Ejecutar». Con «Comparar» ambos métodos se ejecutan 20 veces y se muestran los tiempos promedio.
""";

    public static final String COMPLEJIDAD = """
ANÁLISIS DE COMPLEJIDAD

Notaciones comunes (de menor a mayor crecimiento)
    O(1)        Constante            Acceso a un arreglo
    O(log n)    Logarítmica          Búsqueda binaria
    O(n)        Lineal               Búsqueda lineal
    O(n log n)  Lineal-logarítmica   Merge Sort
    O(n²)       Cuadrática           Bubble Sort
    O(2ⁿ)       Exponencial          Generación de subconjuntos
    O(n!)       Factorial            Generación de permutaciones

Notaciones asintóticas
    Big O (O): cota superior asintótica. «El algoritmo no es peor que…».
    Omega (Ω): cota inferior asintótica. «El algoritmo no es mejor que…».
    Theta (Θ): cota ajustada; se cumple cuando O y Ω coinciden.

Análisis de casos
    Mejor caso: la entrada más favorable (ej.: el valor buscado está primero).
    Caso promedio: comportamiento esperado con entradas típicas.
    Peor caso: la entrada más desfavorable (ej.: el valor no existe). Big O suele referirse a este caso.

Interpretación de Big O
Una complejidad mayor no implica que un algoritmo sea «malo». Para n pequeño, un O(n²) simple puede ser más rápido que un O(n log n) con mucha sobrecarga. La conveniencia depende del tamaño de entrada, del contexto y de los recursos disponibles.

En la pestaña Práctica elige una complejidad, ingresa n y observa operaciones, tiempo y la gráfica de crecimiento.
""";

    public static final String[] CLASES = {
"""
CLASE P (tiempo polinómico)
Problemas que se RESUELVEN en tiempo polinómico O(n^k).
Características: existen algoritmos eficientes; se consideran «tratables».
Ejemplo: búsqueda lineal O(n) y búsqueda binaria O(log n).
Práctica: ingresa un arreglo y un valor; se ejecutan ambas búsquedas y se comparan sus operaciones.
""",
"""
CLASE NP (polinómico no determinista)
Problemas cuyas soluciones se pueden VERIFICAR en tiempo polinómico. Todo problema de P está en NP.
Ejemplo: Subset Sum. Dado un conjunto, un objetivo y un certificado (subconjunto propuesto), verificar que suma el objetivo es rápido.
Práctica: propone un certificado y el programa lo verifica. Para ver lo difícil que es ENCONTRARLO, usa NP-completo.
""",
"""
CLASE NP-COMPLETO
Problemas que pertenecen a NP y además son NP-difíciles: son los «más difíciles» de NP. Si se resolviera uno en tiempo polinómico, se resolverían todos.
Ejemplo: Subset Sum (versión de decisión: ¿existe un subconjunto con suma = objetivo?).
Práctica: búsqueda por fuerza bruta que examina los 2ⁿ subconjuntos (máx. 22 elementos).
""",
"""
CLASE NP-DIFÍCIL
Problemas al menos tan difíciles como los de NP; no necesitan estar en NP (su solución puede no ser verificable en tiempo polinómico).
Ejemplo: Problema del viajante de comercio (TSP), versión de optimización: hallar la ruta de menor costo que visita todas las ciudades y regresa al inicio.
Práctica: fuerza bruta sobre una instancia pequeña (3 a 10 ciudades): se evalúan (n−1)! recorridos.
"""};

    public static final String[] GRAFOS = {
"""
KRUSKAL – ÁRBOL DE EXPANSIÓN MÍNIMA
Un árbol de expansión mínima (MST) conecta TODOS los vértices con el menor costo total y sin ciclos.
Algoritmo: ordenar aristas por peso y aceptar cada una si no forma ciclo (Union-Find).
Aplicación: diseño de redes, conexión de ciudades.
Complejidad: O(E log E).
""",
"""
KAHN – ORDENAMIENTO TOPOLÓGICO
Ordena los vértices de un grafo dirigido acíclico (DAG) de modo que cada dependencia aparezca antes de lo que depende de ella.
Algoritmo: calcular grados de entrada; procesar los vértices con grado 0 y descontar el grado de sus sucesores.
Aplicación: organización de tareas, secuencia de actividades. Si no se procesan todos los vértices, hay un ciclo.
Complejidad: O(V + E).
""",
"""
DIJKSTRA – CAMINOS MÍNIMOS
Calcula la ruta de menor costo desde un origen hacia los demás vértices (pesos no negativos).
Algoritmo: se fija el vértice no visitado de menor distancia y se «relajan» sus aristas.
Aplicación: rutas entre ubicaciones.
Complejidad: O((V + E) log V) con cola de prioridad.
Elige origen y destino en la barra superior.
""",
"""
VIAJANTE DE COMERCIO (TSP)
Hallar el recorrido de menor costo que visita cada ciudad una vez y regresa al inicio. Es NP-difícil.
Algoritmo mostrado: fuerza bruta; fija la ciudad A y prueba las (n−1)! permutaciones restantes.
Complejidad: O(n!). Con programación dinámica (Held-Karp): O(n²·2ⁿ).
Aplicación: recorrido de varias ciudades, logística.
"""};
}
