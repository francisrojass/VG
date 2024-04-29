package ej_4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EmparejamientoEdge(EmparejamientoVertex source, EmparejamientoVertex target, Integer action, Double weight) 
	implements SimpleEdgeAction<EmparejamientoVertex, Integer> {
	public static EmparejamientoEdge of(EmparejamientoVertex v1, EmparejamientoVertex v2, Integer a) {
		
		// el v1.index() todavia nose si es la persona o no, pero si es persona segura ya que viene de una opcion
		double peso = a%2 ==0 ? FactoriaEmparejamiento.getAfinidad(v1.index(), a) : 0;
		
		return new EmparejamientoEdge(v1, v2, a, peso);
	}

}
