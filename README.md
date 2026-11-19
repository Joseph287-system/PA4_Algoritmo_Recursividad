# PA4_Algoritmo_Recursividad
## Objetivo del proyecto
Proponer y desarrollar una solución algorítmica para un **módulo inteligente de orientación dentro del campus**, integrando recursividad, ordenamiento recursivo y grafos (DFS, BFS y A*) para organizar información del campus, analizar rutas y justificar decisiones técnicas.

## Integrantes y organización del equipo
- **[Joseph Antonio Fong Millones]:** Actividad 1 – Recursividad aplicada (`ZonaCampus.java` y `Recursividad.java`).
- **[Integrante 2]:** Actividad 2 – Ordenamiento recursivo.
- **[Integrante 3]:** Actividad 3 – Modelado con grafos (DFS, BFS y A*).
- **[Integrante 4]:** Actividad 4 – Argumentación final, relación con IA, informe PDF y video.

## Resumen del trabajo: Actividad 1 – Recursividad aplicada

### Enfoque y relación con *Divide y vencerás*
La recursividad consiste en que una función se llame a sí misma sobre instancias más pequeñas hasta llegar a un caso base. En el contexto del módulo de orientación, se articula con *Divide y vencerás* para descomponer la estructura jerárquica del campus (**Campus → Pabellones → Pisos/Áreas → Aulas o Laboratorios**) en subzonas más pequeñas, resolver el cálculo en cada nivel y combinar los resultados obtenidos.

### ¿Qué resuelve el algoritmo implementado?
En `ZonaCampus.java` y `Recursividad.java` (paquete `pa4_algoritmo`) se implementa `contarEspaciosAccesibles(zona)`, un algoritmo recursivo que recorre la jerarquía de zonas del campus para contar cuántos ambientes **accesibles** (aptos para movilidad reducida) y **disponibles** existen a partir de una zona dada:
- **Caso base:** Cuando una zona no tiene subzonas (`subzonas` vacía), representa un ambiente terminal (como un aula o laboratorio) y devuelve `1` si es accesible y está disponible, o `0` en caso contrario (y `0` si la zona es nula).
- **Caso recursivo:** Cuando una zona contiene subzonas, invoca `contarEspaciosAccesibles(sub)` en cada subzona y acumula los resultados parciales en un total.
- **Condición de finalización:** Al tratarse de una jerarquía finita y sin ciclos, cada llamada desciende un nivel hasta alcanzar nodos terminales (sin subzonas), garantizando que el proceso termine en un número finito de pasos.

## Siguientes secciones por completar en equipo
- **Actividad 2 (Ordenamiento recursivo):** [Resumen del algoritmo y criterio de ordenamiento].
- **Actividad 3 (Grafos – DFS, BFS y A*):** [Resumen del grafo, nodos, aristas y comparación de recorridos].
- **Actividad 4 (Conclusión e IA):** [Justificación técnica y vínculo con IA].
- **Video de exposición (YouTube):** [Enlace al video con cámaras encendidas]
