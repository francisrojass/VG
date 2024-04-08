package ej_4;

import java.util.List;
import java.util.Set;

import us.lsi.graphs.virtual.VirtualVertex;

public record EmparejamientoVertex(Integer index, Integer i, Set<Integer> restante, Integer ultima) 
	implements VirtualVertex<EmparejamientoVertex, EmparejamientoEdge, Integer> {
	
	public static EmparejamientoVertex of(Integer i1, Integer i2, Set<Integer> r, Integer u) {
		return new EmparejamientoVertex(i1, i2, r, u);
	}
	@Override
	public List<Integer> actions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmparejamientoVertex neighbor(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmparejamientoEdge edge(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

}
