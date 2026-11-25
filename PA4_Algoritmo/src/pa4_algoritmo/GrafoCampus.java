package pa4_algoritmo;

import java.util.*;

/**
 * Grafo del campus para el modulo inteligente de orientacion.
 * Cada vertice representa una ubicacion y cada arista un camino.
 * El peso representa la distancia aproximada en metros.
 */
public class GrafoCampus {

    private final Map<String, List<Arista>> adyacencia = new LinkedHashMap<>();

    private static class Arista {
        String destino;
        double distancia;
        boolean accesible;

        Arista(String destino, double distancia, boolean accesible) {
            this.destino = destino;
            this.distancia = distancia;
            this.accesible = accesible;
        }
    }

    private static class Estado {
        String nodo;
        double costoAcumulado;
        double prioridad;

        Estado(String nodo, double costoAcumulado, double prioridad) {
            this.nodo = nodo;
            this.costoAcumulado = costoAcumulado;
            this.prioridad = prioridad;
        }
    }

    // Agrega una ubicacion (nodo) al grafo.
    public void agregarNodo(String nodo) {
        adyacencia.putIfAbsent(nodo, new ArrayList<>());
    }

    // Agrega un camino en ambos sentidos.
    public void agregarConexion(String origen, String destino,
                                double distancia, boolean accesible) {
        agregarNodo(origen);
        agregarNodo(destino);

        adyacencia.get(origen).add(new Arista(destino, distancia, accesible));
        adyacencia.get(destino).add(new Arista(origen, distancia, accesible));
    }

    // DFS: explora primero un camino hasta donde sea posible.
    public List<String> dfs(String inicio) {
        List<String> recorrido = new ArrayList<>();
        if (!adyacencia.containsKey(inicio)) return recorrido;

        Set<String> visitados = new HashSet<>();
        dfsRecursivo(inicio, visitados, recorrido);
        return recorrido;
    }

    private void dfsRecursivo(String actual, Set<String> visitados,
                              List<String> recorrido) {
        visitados.add(actual);
        recorrido.add(actual);

        for (Arista arista : adyacencia.get(actual)) {
            if (!visitados.contains(arista.destino)) {
                dfsRecursivo(arista.destino, visitados, recorrido);
            }
        }
    }

    // BFS: encuentra una ruta con el menor numero de conexiones.
    // No necesariamente es la ruta de menor distancia.
    public List<String> bfs(String inicio, String destino) {
        if (!adyacencia.containsKey(inicio) || !adyacencia.containsKey(destino)) {
            return Collections.emptyList();
        }

        Queue<String> cola = new LinkedList<>();
        Map<String, String> anterior = new HashMap<>();
        Set<String> visitados = new HashSet<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            if (actual.equals(destino)) break;

            for (Arista arista : adyacencia.get(actual)) {
                if (visitados.add(arista.destino)) {
                    anterior.put(arista.destino, actual);
                    cola.add(arista.destino);
                }
            }
        }

        return reconstruirRuta(inicio, destino, anterior);
    }

    // A*: busca una ruta de menor costo usando distancia y una heuristica.
    // La heuristica debe estimar el costo restante hasta el destino.
    // Si solo se conoce la distancia, se puede usar 0 para todos los nodos;
    // en ese caso A* funciona como Dijkstra.
    public List<String> aEstrella(String inicio, String destino,
                                  Map<String, Double> heuristica,
                                  boolean soloAccesibles) {
        if (!adyacencia.containsKey(inicio) || !adyacencia.containsKey(destino)) {
            return Collections.emptyList();
        }

        Map<String, Double> costo = new HashMap<>();
        Map<String, String> anterior = new HashMap<>();
        PriorityQueue<Estado> abiertos =
                new PriorityQueue<>(Comparator.comparingDouble(e -> e.prioridad));

        for (String nodo : adyacencia.keySet()) {
            costo.put(nodo, Double.POSITIVE_INFINITY);
        }

        costo.put(inicio, 0.0);
        abiertos.add(new Estado(inicio, 0.0, estimar(inicio, heuristica)));

        while (!abiertos.isEmpty()) {
            Estado estado = abiertos.poll();
            String actual = estado.nodo;

            if (estado.costoAcumulado > costo.get(actual)) continue;
            if (actual.equals(destino)) {
                return reconstruirRuta(inicio, destino, anterior);
            }

            for (Arista arista : adyacencia.get(actual)) {
                if (soloAccesibles && !arista.accesible) continue;

                double nuevoCosto = costo.get(actual) + arista.distancia;
                if (nuevoCosto < costo.get(arista.destino)) {
                    costo.put(arista.destino, nuevoCosto);
                    anterior.put(arista.destino, actual);

                    double prioridad = nuevoCosto + estimar(arista.destino, heuristica);
                    abiertos.add(new Estado(arista.destino, nuevoCosto, prioridad));
                }
            }
        }

        return Collections.emptyList();
    }

    private double estimar(String nodo, Map<String, Double> heuristica) {
        return heuristica.getOrDefault(nodo, 0.0);
    }

    private List<String> reconstruirRuta(String inicio, String destino,
                                         Map<String, String> anterior) {
        if (!inicio.equals(destino) && !anterior.containsKey(destino)) {
            return Collections.emptyList();
        }

        LinkedList<String> ruta = new LinkedList<>();
        String actual = destino;

        while (actual != null) {
            ruta.addFirst(actual);
            if (actual.equals(inicio)) break;
            actual = anterior.get(actual);
        }

        if (ruta.isEmpty() || !ruta.getFirst().equals(inicio)) {
            return Collections.emptyList();
        }
        return ruta;
    }

    // Construye un ejemplo de campus para probar los algoritmos.
    public static void main(String[] args) {
        GrafoCampus campus = new GrafoCampus();

        campus.agregarConexion("Entrada principal", "Pabellon A", 40, true);
        campus.agregarConexion("Entrada principal", "Pabellon B", 55, true);
        campus.agregarConexion("Pabellon A", "Aulas", 20, true);
        campus.agregarConexion("Pabellon A", "Laboratorio", 25, false);
        campus.agregarConexion("Pabellon B", "Biblioteca", 30, true);
        campus.agregarConexion("Pabellon B", "Cafeteria", 18, true);
        campus.agregarConexion("Aulas", "Biblioteca", 35, true);
        campus.agregarConexion("Laboratorio", "Cafeteria", 45, true);

        System.out.println("Recorrido DFS desde la entrada:");
        System.out.println(campus.dfs("Entrada principal"));

        System.out.println("\nRuta BFS hasta la biblioteca:");
        System.out.println(campus.bfs("Entrada principal", "Biblioteca"));

        // Valores ilustrativos de distancia estimada hasta la biblioteca.
        Map<String, Double> heuristica = new HashMap<>();
        heuristica.put("Entrada principal", 60.0);
        heuristica.put("Pabellon A", 45.0);
        heuristica.put("Pabellon B", 25.0);
        heuristica.put("Aulas", 30.0);
        heuristica.put("Laboratorio", 40.0);
        heuristica.put("Biblioteca", 0.0);
        heuristica.put("Cafeteria", 20.0);

        System.out.println("\nRuta A* accesible hasta la biblioteca:");
        System.out.println(campus.aEstrella(
                "Entrada principal", "Biblioteca", heuristica, true));
    }
}
