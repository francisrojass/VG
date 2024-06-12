
Tema 7: Grafos Virtuales - Parte 1
Este repositorio contiene el código relacionado con el Modelado de Grafos Virtuales. Esta parte del proyecto se enfoca en la creación de estructuras de datos y algoritmos para manipular grafos virtuales, así como en la implementación de heurísticas para resolver problemas asociados.

Modelado Grafo Virtual
a) Tipo Vertice
El tipo de vértice VirtualVertex<V, E, A> se define con propiedades que facilitan la implementación y manipulación de grafos virtuales. Las propiedades incluyen:

actions(): Método que devuelve las alternativas del vértice actual.
neighbor(A a): Método que devuelve el siguiente vértice (vecino) al actual según la alternativa proporcionada.
Además, se proporciona un método edge(A a) para crear una arista entre el vértice actual y su vecino, simplificando así la creación y manipulación de aristas.

b) Tipo Arista
El tipo de arista se implementa mediante el uso de un record que cumple con la interfaz SimpleEdgeAction. Este record incluye propiedades como el origen (source), el destino (target), la acción (action) y el peso (weight). Se proporciona un método estático of para la creación de aristas.

c) Heurística
La heurística es una estimación del peso del camino desde el vértice actual hasta un vértice final en el grafo. Debe ser admisible, es decir:

Para problemas de minimización, la heurística debe ser una cota inferior al peso real.
Para problemas de maximización, la heurística debe ser una cota superior al peso real.
Esta estimación optimista ayuda a guiar los algoritmos de búsqueda en la dirección correcta.

Tema 7: Grafos Virtuales - Parte 2
Este apartado cubre algoritmos y técnicas específicas para resolver problemas utilizando grafos virtuales.

7.1 Introducción al Tipo de Problema
Los algoritmos implementados en esta parte generan vértices solamente, como una secuencia de vértices. El tipo de arista desaparece en esta etapa, y el vértice ya no necesita implementar VirtualVertex. Se introduce un nuevo tipo de problema para representar los vértices en esta etapa.

7.2 Esquema de Algoritmo Voraz (Alg. Voraz)
Se presenta un esquema de algoritmo voraz para resolver problemas secuenciales. Este algoritmo elige la mejor opción en cada paso localmente, sin considerar el futuro, lo que no garantiza una solución óptima.

7.3 Back-Tracking (Vuelta atrás) (Alg. Recursivo)
Se describe el esquema de back-tracking para explorar exhaustivamente todas las posibles soluciones de un problema. Se define el tipo de estado y se presenta el esquema de back-tracking para encontrar la solución óptima.

7.4 Programación Dinámica con Reducción (P.D.R) (Alg. Recursivo)
Se introduce la técnica de programación dinámica con reducción para problemas recursivos. Se define el tipo SP y se presenta el esquema de programación dinámica con reducción para resolver problemas de manera eficiente.

7.5 A* (Alg. Voraz)
Se menciona brevemente el algoritmo A* como una técnica de búsqueda informada, aunque no es parte de la práctica ni del examen.

Modelo para Ejercicio 4
Se proporciona un modelo específico para el ejercicio 4, que implica la manipulación de una lista de parejas y una lista de personas disponibles. El modelo detalla las propiedades y métodos necesarios para resolver este problema en particular.
