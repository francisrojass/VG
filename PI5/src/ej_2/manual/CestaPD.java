package ej_2.manual;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ej_2.FactoriaCesta;
import ej_2.SolucionCesta;
import us.lsi.common.List2;
import us.lsi.common.Map2;

public class CestaPD {

	public static record Spc(Integer a, Integer weight) implements Comparable<Spc>{
		
		public static Spc of(Integer a, Integer weight) {
			return new Spc(a, weight);
		}
		
		@Override
		public int compareTo(Spc sp) {
			return this.weight.compareTo(sp.weight);
		}
		
	}
	
	public static Map<CestaProblem, Spc> memory;
	public static Integer mejorValor = Integer.MAX_VALUE; //Estamos minimizando por eso es max_value
	
	public static SolucionCesta search() {
		memory = Map2.empty();
		mejorValor = Integer.MAX_VALUE;
		
		pdr_search(CestaProblem.initial(), 0, memory);
		return getSolucion();
	}
	
	private static Spc pdr_search(CestaProblem prob, Integer acumulado, Map<CestaProblem, Spc> memoria) {
		/*
		 * valorAcumulado, peso de cada arista
		 */
		Spc res = null;
		Boolean esTerminal = prob.index().equals(FactoriaCesta.getNumProductos());
		Boolean esSolucion = prob.categoriasPorCubrir().size() == 0;
		
		if(memoria.containsKey(prob)) {
			res = memoria.get(prob);
		} else if (esSolucion) {
			res = Spc.of(null, 0);
			/*
			 * 	le damos null y 0, cuando llega al final,por que, null, por que ya no tenemos que coger ninguna alternativa
			 por que cero?, por que es el peso del camino al que me lleva a la solucion por eso es cero, 
			por que ya estoy en ellla, en los vertices de arriba no sera cero ya que tendra el peso del 
			camino para que sea solucion, 
			 */
			memoria.put(prob, res);
			if (acumulado < mejorValor) {
				/*
				 * Si acum es menor que el mejor valor actualizo ya que estoy minimizando.
				 */
				mejorValor = acumulado;
			}
		} else {
			List<Spc> soluciones = List2.empty();
			for(Integer action: prob.actions()) {
				Double cota = acotar(acumulado, prob, action);
				if (acumulado + cota < mejorValor) {
					Spc sh = pdr_search(prob.neighbor(action), acumulado + darPrecio(action, prob), memoria);
					if (sh!=null) {
						res = Spc.of(action, sh.weight + darPrecio(action, prob));
						soluciones.add(res);
					}
				}
				
			}
			if (res != null)
				res = soluciones.stream().min(Comparator.naturalOrder()).orElse(null);
				memoria.put(prob, res);
		}
		return res;
	}
	private static Integer darPrecio(Integer action, CestaProblem prob) {
		if (action==1) {
			return FactoriaCesta.getPrecio(prob.index());
		}
		return 0;
	}
	private static Double acotar(Integer acumulado, CestaProblem prob, Integer action) {
		// Valor acumulado hasta el estado actual
	    Integer valorAcumulado = acumulado;

	    // Costo de pasar al próximo estado considerando la acción tomada
	    Double paso = (double) FactoriaCesta.getPrecio(action);

	    // Heurística que proporciona una estimación del costo adicional basado en el próximo estado
	    Integer heuristica = heuristica(prob.index(), action, prob);

	    return (double) (valorAcumulado + paso + heuristica);
	}
	public static Double heuristic(CestaProblem v1) {
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
	private static Integer heuristica(Integer index, Integer action, CestaProblem prob) {
		// Obtiene el precio del producto correspondiente a la acción
	    Integer precio = FactoriaCesta.getPrecio(index);
	    
	    // Retorna el precio como heurística
	    return precio;
	}
	
	private static SolucionCesta getSolucion() {
		List<Integer> acciones = List2.empty();
		CestaProblem prob = CestaProblem.initial();
		Spc spc = memory.get(prob);
		while (spc != null && spc.a != null) {
			CestaProblem old = prob;
			acciones.add(spc.a);
			prob = old.neighbor(spc.a);
			spc = memory.get(prob);
		}
		return SolucionCesta.create(acciones);
	}
	
	
}
