# Conjunto de Problemas resueltos por grafos virtuales

## Problema 1: Planificación de Cultivos

El agricultor posee varios terrenos, cada uno de diferentes tamaños en metros cuadrados. El objetivo es planificar la siembra de diferentes variedades de verduras. Cada variedad requiere una cantidad específica de metros cuadrados y puede ser seleccionada o no para su siembra. Además, algunas variedades pueden ser incompatibles entre sí, lo que significa que no pueden ser sembradas en el mismo terreno. El objetivo es maximizar la cantidad de variedades de verduras sembradas, cumpliendo con las restricciones de espacio y compatibilidad entre ellas.

## Problema 2: Composición de Cesta de Navidad

Se desea componer una cesta de Navidad que incluya diversos productos. De cada producto se conoce su categoría, su precio y su valoración (un entero entre 1 y 5). Determinar la composición de la cesta de forma que:
- entre los productos seleccionados se cubran todas las categorías,
- la media de las valoraciones de todos los productos seleccionados sea mayor o igual a 3,
- el precio total de los productos seleccionados de una misma categoría no supere un presupuesto dado (este presupuesto es común a todas las categorías), y
- se minimice el precio total de la cesta.

## Problema 3: Distribución de Productos

Un distribuidor de productos debe decidir la cantidad de unidades de diferentes productos para enviar a distintos destinos. De cada destino se conoce la demanda mínima de cantidad total de productos a almacenar en ese destino, que debe cumplirse. Además, para cada tipo de producto y cada destino se tiene información sobre el costo de almacenamiento de cada unidad de producto en dicho destino. El objetivo es saber cuántas unidades de cada producto enviar a cada destino, minimizando el costo total de almacenamiento y cumpliendo con las demandas mínimas de productos de cada destino.

## Problema 4: Emparejamiento de Individuos

Dada una lista de personas, es necesario emparejarlas de dos en dos (suponiendo que siempre hay un número par de personas). De cada persona se conoce el conjunto de idiomas que habla, su edad y su nacionalidad. También se conoce la afinidad que existe entre cada par de personas (un entero en el rango de 0 a 5). El objetivo es obtener una solución que maximice la suma total de las afinidades obtenidas con los emparejamientos realizados, teniendo en cuenta que las personas emparejadas deben tener:
- al menos un idioma en común,
- como máximo 5 años de diferencia de edad, y
- distinta nacionalidad.

# Grafos Virtuales - Teoria para entenderlo

Este repositorio contiene el código relacionado con el Modelado de Grafos Virtuales. Esta parte del proyecto se enfoca en la creación de estructuras de datos y algoritmos para manipular grafos virtuales, así como en la implementación de heurísticas para resolver problemas asociados.

## Tema 7: Grafos Virtuales - Parte 1

### Modelado Grafo Virtual

#### Tipo Vertice

El tipo de vértice `VirtualVertex<V, E, A>` se define con propiedades que facilitan la implementación y manipulación de grafos virtuales. Las propiedades incluyen:

- `actions()`: Método que devuelve las alternativas del vértice actual.
- `neighbor(A a)`: Método que devuelve el siguiente vértice (vecino) al actual según la alternativa proporcionada.

Además, se proporciona un método `edge(A a)` para crear una arista entre el vértice actual y su vecino, simplificando así la creación y manipulación de aristas.

#### Tipo Arista

El tipo de arista se implementa mediante el uso de un `record` que cumple con la interfaz `SimpleEdgeAction`. Este record incluye propiedades como el origen (`source`), el destino (`target`), la acción (`action`) y el peso (`weight`). Se proporciona un método estático `of` para la creación de aristas.

#### Heurística

La heurística es una estimación del peso del camino desde el vértice actual hasta un vértice final en el grafo. Debe ser admisible, es decir:

- Para problemas de minimización, la heurística debe ser una cota inferior al peso real.
- Para problemas de maximización, la heurística debe ser una cota superior al peso real.

Esta estimación optimista ayuda a guiar los algoritmos de búsqueda en la dirección correcta.

## Tema 7: Grafos Virtuales - Parte 2

Este apartado cubre algoritmos y técnicas específicas para resolver problemas utilizando grafos virtuales.

### 7.1 Introducción al Tipo de Problema

Los algoritmos implementados en esta parte generan vértices solamente, como una secuencia de vértices. El tipo de arista desaparece en esta etapa, y el vértice ya no necesita implementar `VirtualVertex`. Se introduce un nuevo tipo de problema para representar los vértices en esta etapa.

### 7.2 Esquema de Algoritmo Voraz (Alg. Voraz)

Se presenta un esquema de algoritmo voraz para resolver problemas secuenciales. Este algoritmo elige la mejor opción en cada paso localmente, sin considerar el futuro, lo que no garantiza una solución óptima.

### 7.3 Back-Tracking (Vuelta atrás) (Alg. Recursivo)

Se describe el esquema de back-tracking para explorar exhaustivamente todas las posibles soluciones de un problema. Se define el tipo de estado y se presenta el esquema de back-tracking para encontrar la solución óptima.

### 7.4 Programación Dinámica con Reducción (P.D.R) (Alg. Recursivo)

Se introduce la técnica de programación dinámica con reducción para problemas recursivos. Se define el tipo SP y se presenta el esquema de programación dinámica con reducción para resolver problemas de manera eficiente.

### 7.5 A* (Alg. Voraz)

Se menciona brevemente el algoritmo A* como una técnica de búsqueda informada.

## Modelo para Ejercicio 4

Se proporciona un modelo específico para el ejercicio 4, que implica la manipulación de una lista de parejas y una lista de personas disponibles. El modelo detalla las propiedades y métodos necesarios para resolver este problema en particular.




