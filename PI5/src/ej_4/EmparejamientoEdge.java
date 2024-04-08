package ej_4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EmparejamientoEdge(EmparejamientoVertex source, EmparejamientoVertex target, Integer action, Double weight) 
	implements SimpleEdgeAction<EmparejamientoVertex, Integer> {
	public static EmparejamientoEdge of(EmparejamientoVertex v1, EmparejamientoVertex v2, Integer a) {
		return new EmparejamientoEdge(v1, v2, a, null);
	}

}
