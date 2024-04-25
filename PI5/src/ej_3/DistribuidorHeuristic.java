package ej_3;

import java.util.function.Predicate;


public class DistribuidorHeuristic {
	public static Double heuristic(DistribuidorVertex v1, Predicate<DistribuidorVertex> goal, DistribuidorVertex v2) {
		// Calculamos la estimación del costo mínimo de almacenamiento de los productos que aún se demandan
        double costoEstimado = 0.0;
        
        // Recorremos las demandas restantes en el vértice v1
        for (int i = 0; i < v1.demandasRestantes().size(); i++) {
            // Si la demanda es mayor que cero, significa que aún hay productos que se demandan en algún destino
            if (v1.demandasRestantes().get(i) > 0) {
                // Buscamos el costo de almacenamiento del producto en el destino correspondiente
                Integer costoAlmacenamiento = FactoriaDistribuidor.getAlmacenamientoCoste(DistribuidorVertex.saberProducto(i), DistribuidorVertex.saberDestino(i));
                
                // Sumamos al costo estimado el costo de almacenamiento por la cantidad de unidades que aún se demandan
                costoEstimado += costoAlmacenamiento * v1.demandasRestantes().get(i);
            }
        }
        
        return costoEstimado;
	}
}
