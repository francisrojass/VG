package ej_4;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import ej_1.HuertoVertex;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.graphs.virtual.VirtualVertex;

public record EmparejamientoVertex(Integer index, Set<Integer> restante, Integer ultima) 
	implements VirtualVertex<EmparejamientoVertex, EmparejamientoEdge, Integer> {
	public static Integer NumPersonas = FactoriaEmparejamiento.getNumeroPersonas();
	public static EmparejamientoVertex of(Integer i1, Set<Integer> r, Integer u) {
		return new EmparejamientoVertex(i1, r, u);
	}
	public static EmparejamientoVertex initial() {
		return of(0, Set2.range(0, FactoriaEmparejamiento.getNumeroPersonas()-1), FactoriaEmparejamiento.getNumeroPersonas());
	}
	public static Predicate<EmparejamientoVertex> goal(){
		return v -> v.index == NumPersonas;
	}
	public static Predicate<EmparejamientoVertex> goalHasSolution(){
		return v -> v.restante.isEmpty();
	}
	@Override
	public List<Integer> actions() {
		List<Integer> opciones = List2.empty();
		
		if(index < NumPersonas) return opciones;
		
		return opciones;
	}

	@Override
	public EmparejamientoVertex neighbor(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmparejamientoEdge edge(Integer a) {
		return EmparejamientoEdge.of(this, neighbor(a), a);
	}

}
