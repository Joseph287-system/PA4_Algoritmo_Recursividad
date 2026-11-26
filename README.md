# PA4_Algoritmo_Recursividad
## Objetivo del proyecto
Proponer y desarrollar una solución algorítmica para un **módulo inteligente de orientación dentro del campus**, integrando recursividad, ordenamiento recursivo y grafos (DFS, BFS y A*) para organizar información del campus, analizar rutas y justificar decisiones técnicas.

## Integrantes y organización del equipo
- **[Joseph Antonio Fong Millones]:** Actividad 1 – Recursividad aplicada (`ZonaCampus.java` y `Recursividad.java`).
- **[Marco Ychuta Saldivar ]:** Actividad 2 – Ordenamiento recursivo.
- **[Wilmer Jamner Guevara Ramos ]:** Actividad 3 – Modelado con grafos (DFS, BFS y A*).
- **[Javier Alonso Gallegos Rodríguez]:** Actividad 4 – Argumentación final, relación con IA, informe PDF y video.

## Resumen del trabajo: Actividad 1 – Recursividad aplicada

### Enfoque y relación con *Divide y vencerás*
La recursividad consiste en que una función se llame a sí misma sobre instancias más pequeñas hasta llegar a un caso base. En el contexto del módulo de orientación, se articula con *Divide y vencerás* para descomponer la estructura jerárquica del campus (**Campus → Pabellones → Pisos/Áreas → Aulas o Laboratorios**) en subzonas más pequeñas, resolver el cálculo en cada nivel y combinar los resultados obtenidos.

### ¿Qué resuelve el algoritmo implementado?
En `ZonaCampus.java` y `Recursividad.java` (paquete `pa4_algoritmo`) se implementa `contarEspaciosAccesibles(zona)`, un algoritmo recursivo que recorre la jerarquía de zonas del campus para contar cuántos ambientes **accesibles** (aptos para movilidad reducida) y **disponibles** existen a partir de una zona dada:
- **Caso base:** Cuando una zona no tiene subzonas (`subzonas` vacía), representa un ambiente terminal (como un aula o laboratorio) y devuelve `1` si es accesible y está disponible, o `0` en caso contrario (y `0` si la zona es nula).
- **Caso recursivo:** Cuando una zona contiene subzonas, invoca `contarEspaciosAccesibles(sub)` en cada subzona y acumula los resultados parciales en un total.
- **Condición de finalización:** Al tratarse de una jerarquía finita y sin ciclos, cada llamada desciende un nivel hasta alcanzar nodos terminales (sin subzonas), garantizando que el proceso termine en un número finito de pasos.

## *Actividad 2 (Ordenamiento recursivo):

  ## 2.1. Planteen un algoritmo recursivo de ordenamiento aplicable a un conjunto de datos del caso.
Se propone utilizar el algoritmo Merge Sort, que permite ordenar un conjunto de datos de menor a mayor mediante la técnica Divide y Vencerás.
Para desarrollar el ejercicio utilizaremos el siguiente conjunto de números:
[38, 12, 45, 8, 23, 16, 50, 4]
El algoritmo funciona mediante dos condiciones fundamentales:
•	Caso base: cuando la lista contiene uno o ningún elemento, se considera que ya está ordenada.
•	Caso recursivo: la función se llama a sí misma para dividir el conjunto de datos en grupos más pequeños hasta llegar al caso base.



  ## 2.2 Describan paso a paso cómo se divide el problema y cómo se obtiene el resultado final.
El algoritmo Merge Sort desarrolla el proceso mediante las siguientes etapas:
Paso 1. División del conjunto: Se recibe la lista original y se divide en dos grupos:
•	Grupo 1: [38, 12, 45, 8]
•	Grupo 2: [23, 16, 50, 4]

Paso 2. Subdivisión: Cada grupo vuelve a dividirse en partes más pequeñas hasta obtener listas con un solo elemento.
[38] [12] [45] [8] [23] [16] [50] [4]

Paso 3. Caso base: Cuando cada grupo contiene un solo elemento, se detiene la división porque cada elemento individual ya está ordenado.

Paso 4. Combinación: Se comparan los números y se unen de menor a mayor, formando nuevos grupos ordenados.
Por ejemplo:

•	[38] + [12] = [12, 38]

•	[45] + [8] = [8, 45]

•	[23] + [16] = [16, 23]

•	[50] + [4] = [4, 50]

Paso 5. Resultado final: Se combinan los grupos ordenados hasta obtener una sola lista:
•	Resultado del ordenamiento
•	[4,8,12,16,23,38,45,50]
•	Datos ordenados de menor a mayor.


  ## 2.3. Mencionen por qué el enfoque recursivo puede ser útil en este contexto.

El enfoque recursivo es útil porque permite resolver problemas complejos dividiéndolos en subproblemas más pequeños y fáciles de procesar.
En este caso, Merge Sort facilita el ordenamiento de grandes cantidades de datos sin tener que procesar todos los elementos directamente en una sola operación.
Además, utiliza el principio Divide y Vencerás, que permite organizar el proceso de manera estructurada y presenta una complejidad temporal de \(O(n\log n)\).
Por lo tanto, este enfoque resulta conveniente para trabajar con grandes conjuntos de información, facilitando su organización, procesamiento y análisis.


# 01. DEFINIR LOS DATOS
numeros = [38, 12, 45, 8, 23, 16, 50, 4]

# 02. ALGORITMO RECURSIVO MERGE SORT

def merge_sort(lista):

    # Caso base
    if len(lista) <= 1:
        return lista

    # Dividir el conjunto
    medio = len(lista) // 2

    izquierda = lista[:medio]
    derecha = lista[medio:]

    # Llamadas recursivas
    izquierda = merge_sort(izquierda)
    derecha = merge_sort(derecha)

    # Combinar los resultados
    return combinar(izquierda, derecha)

# 03. COMBINAR LAS LISTAS

def combinar(izquierda, derecha):

    resultado = []
    i = 0
    j = 0
    while i < len(izquierda) and j < len(derecha):
        if izquierda[i] <= derecha[j]:
            resultado.append(izquierda[i])
            i += 1
        else:
            resultado.append(derecha[j])
            j += 1
    resultado.extend(izquierda[i:])
    resultado.extend(derecha[j:])
    return resultado

# 04. EJECUTAR EL ALGORITMO
resultado = merge_sort(numeros)

# 05. MOSTRAR RESULTADOS

print("Lista original:", numeros)

print("Lista ordenada:", resultado)

<img width="1155" height="252" alt="image" src="https://github.com/user-attachments/assets/6f2eb750-41d6-4e6c-bc73-874a15c79e6d" />



- **Actividad 3 (Grafos – DFS, BFS y A*):** [Resumen del grafo, nodos, aristas y comparación de recorridos].package pa4_algoritmo;

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
  

- ## Actividad 4: Argumentación final y relación con inteligencia artificial

En la solución para el módulo inteligente de orientación dentro del campus se integraron técnicas clave para abordar los desafíos del problema:

- La **recursividad** permitió gestionar la estructura jerárquica del campus a través del principio *Divide y Vencerás*, dividiendo el problema en subproblemas manejables (zonas, subzonas y ambientes). Esto facilitó tareas como el conteo de espacios accesibles, asegurando claridad y terminación efectiva del algoritmo.

- El algoritmo **Merge Sort**, de enfoque recursivo, resultó eficiente para ordenar grandes conjuntos de datos relacionados con rutas y ubicaciones, ayudando a organizar la información para su análisis y procesamiento posterior.

- El modelado del campus mediante un **grafo ponderado y etiquetado** permitió representar adecuadamente ubicaciones y caminos, incorporando distancias y accesibilidad. En este contexto, se aplicaron tres algoritmos de búsqueda:

  - **DFS**, útil para una exploración exhaustiva que descubre conexiones y estructuras dentro del grafo.  
  - **BFS**, que halla caminos con el menor número de conexiones en grafos no ponderados.  
  - **A\***, el más recomendado para la búsqueda de rutas óptimas, que combina costos reales y heurísticos para encontrar caminos eficientes y filtrando accesibilidad según criterio.

Por lo tanto, se recomienda una solución híbrida que utilice recursividad para procesar la jerarquía de zonas y el algoritmo **A\*** para la búsqueda optimizada de rutas dentro del grafo. Este enfoque garantiza eficiencia, claridad, y adecuación a las necesidades específicas del usuario.

Esta propuesta está alineada con técnicas fundamentales de inteligencia artificial, como la búsqueda heurística y la descomposición recursiva de problemas complejos, aplicadas a un entorno real. El sistema resultante aporta una base sólida para la toma de decisiones inteligentes y la orientación efectiva en el campus universitario.



- **Video de exposición (YouTube):** [Enlace al video con cámaras encendidas]
- https://youtu.be/GDVOPBFodK8
- 
