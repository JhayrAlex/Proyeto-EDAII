package ec.edu.epn.eda2.grafos;

/** Grafos de ejemplo usados en las demostraciones. */
public class Ejemplos {

    /** Red de ciudades (no dirigido, ponderado): Kruskal y Dijkstra. */
    public static Grafo ciudades() {
        Grafo g = new Grafo(false, true);
        Vertice a = g.agregarVertice("A", 0.05, 0.50), b = g.agregarVertice("B", 0.32, 0.10),
                c = g.agregarVertice("C", 0.32, 0.90), d = g.agregarVertice("D", 0.68, 0.10),
                e = g.agregarVertice("E", 0.68, 0.90), f = g.agregarVertice("F", 0.95, 0.50);
        g.agregarArista(a, c, 2); g.agregarArista(a, b, 4); g.agregarArista(b, c, 1);
        g.agregarArista(b, d, 5); g.agregarArista(c, d, 8); g.agregarArista(c, e, 10);
        g.agregarArista(d, e, 2); g.agregarArista(d, f, 6); g.agregarArista(e, f, 3);
        return g;
    }

    /** Dependencias entre tareas (dirigido, sin pesos): Kahn. */
    public static Grafo tareas() {
        Grafo g = new Grafo(true, false);
        Vertice an = g.agregarVertice("Análisis", 0.07, 0.50), di = g.agregarVertice("Diseño", 0.30, 0.15),
                co = g.agregarVertice("Código", 0.57, 0.15), pr = g.agregarVertice("Pruebas", 0.82, 0.15),
                dc = g.agregarVertice("Docs", 0.45, 0.85), en = g.agregarVertice("Entrega", 0.93, 0.65);
        g.agregarArista(an, di, 0); g.agregarArista(an, dc, 0); g.agregarArista(di, co, 0);
        g.agregarArista(di, dc, 0); g.agregarArista(co, pr, 0); g.agregarArista(pr, en, 0);
        g.agregarArista(dc, en, 0);
        return g;
    }

    /** 5 ciudades con todas las conexiones (grafo completo): viajante de comercio. */
    public static Grafo viajante() {
        Grafo g = new Grafo(false, true);
        double[][] pos = {{0.12, 0.25}, {0.50, 0.08}, {0.88, 0.30}, {0.72, 0.88}, {0.22, 0.82}};
        String[] nom = {"A", "B", "C", "D", "E"};
        for (int i = 0; i < 5; i++) g.agregarVertice(nom[i], pos[i][0], pos[i][1]);
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                int w = (int) Math.round(Math.hypot(pos[i][0] - pos[j][0], pos[i][1] - pos[j][1]) * 100);
                g.agregarArista(g.vertices.get(i), g.vertices.get(j), w);
            }
        }
        return g;
    }
}
