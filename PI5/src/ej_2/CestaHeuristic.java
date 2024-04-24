package ej_2;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class CestaHeuristic {
	
	public static Double heuristic(CestaVertex v1, Predicate<CestaVertex> goal, CestaVertex v2) {
	    List<Integer> presupuestoRestante = v1.presupuestoRestante();
	    Set<Integer> categoriasPorCubrir = v1.categoriasPorCubrir();
	    Integer acumValoracion = v1.acumValoracion();
	    
	    double estimacionPrecioMinimo = 0.0;
	    
	    for (int categoria : categoriasPorCubrir) {
	        int presupuestoCategoria = presupuestoRestante.get(categoria);
	        int precioMinimoCategoria = FactoriaCesta.getPrecioMinimoCategoria(categoria); // Obtener el precio mínimo de la categoría
	        
	        estimacionPrecioMinimo += Math.min(presupuestoCategoria, precioMinimoCategoria);
	    }
	    
	    // Añadir un ajuste por la valoración acumulada
	    double ajusteValoracion = Math.max(0, (3 * FactoriaCesta.getNumCategorias() - acumValoracion)); // Se asume que cada categoría tiene un precio de 3
	    
	    return estimacionPrecioMinimo + ajusteValoracion;
	}

}
