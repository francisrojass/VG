package ej_1;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record HuertoEdge(HuertoVertex source, HuertoVertex target, 
		Integer action, Double weight)
	implements SimpleEdgeAction<HuertoVertex, Integer> {
	//action decision que se toma que cojamos una arista o otra, de tipo del que convenga
	
	public static HuertoEdge of(HuertoVertex v1, HuertoVertex v2, Integer a) {
		return new HuertoEdge(v1, v2, a, null); //el ultimo parametro sera la alternativa(Huerto donde se planta la variedad)
	}
}
