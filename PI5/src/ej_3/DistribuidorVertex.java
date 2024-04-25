package ej_3;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ej_3.FactoriaDistribuidor.Destino;
import ej_3.FactoriaDistribuidor.Productos;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record DistribuidorVertex(Integer index, List<Integer> unidadesRestantes, List<Integer> demandasRestantes) 
	implements VirtualVertex<DistribuidorVertex, DistribuidorEdge, Integer> {
	
	public static List<Destino> ListaDestino = FactoriaDistribuidor.getDestinos();
	
	public static List<Productos> ListaProductos = FactoriaDistribuidor.getProductos();
	
	public static DistribuidorVertex of(Integer i, List<Integer> u, List<Integer> d) {
		return new DistribuidorVertex(i, u, d);
	}
	
	public static DistribuidorVertex initial() {
		return of(0, creaUnidadesRestantes(), creaDemandasRestantes());
	}
	
	public static List<Integer> creaUnidadesRestantes(){
		return FactoriaDistribuidor.ListaProductos.stream()
					.map(x -> x.unidades())
					.collect(Collectors.toList());
	}

	public static List<Integer> creaDemandasRestantes(){
		return FactoriaDistribuidor.ListaDestino.stream()
					.map(x -> x.demandaMinima())
					.collect(Collectors.toList());
	}
	public static Predicate<DistribuidorVertex> goal() {
		return v -> v.index() == (ListaDestino.size() * ListaProductos.size());
	}
	public static Predicate<DistribuidorVertex> goalHasSolution() {
		return v -> true;
	}
	@Override
	public List<Integer> actions() {
		List<Integer> opciones = List2.empty();
		
		if(index < ListaDestino.size() * ListaProductos.size()) {
			if(demandasRestantes.get(saberDestino(index)) > 0 && 
			   unidadesRestantes.get(saberProducto(index)) > 0) {
				
				if(unidadesRestantes.get(saberProducto(index)) > demandasRestantes.get(saberDestino(index))) {
					for (int i = 1; i < unidadesRestantes.get(saberProducto(index)); i++) {
						opciones.add(i);
					}
				}
			}
			opciones.add(0); //siempre esta la opcion de no enviar nada
		}
		
		return opciones;
	}
	public static Integer saberDestino(Integer i) {
		Integer NumDestinos = FactoriaDistribuidor.getNumDestinos();
		return i%NumDestinos;
	}
	public static Integer saberProducto(Integer i) {
		Integer NumDestinos = FactoriaDistribuidor.getNumDestinos();
		return i/NumDestinos;
	}
	@Override
	public DistribuidorVertex neighbor(Integer a) {
		List<Integer> NewUnidadesRestantes = List2.copy(unidadesRestantes);
		List<Integer> NewDemandasRestantes = List2.copy(demandasRestantes);
		
		if(a > 0) {
			NewUnidadesRestantes.set(saberProducto(index), NewUnidadesRestantes.get(saberProducto(index))-a);
			NewDemandasRestantes.set(saberDestino(index), NewDemandasRestantes.get(saberDestino(index)) - a);
		}
		
		return of(index + 1, NewUnidadesRestantes, NewDemandasRestantes);
	}

	@Override
	public DistribuidorEdge edge(Integer a) {
		return DistribuidorEdge.of(this, neighbor(a), a);
	}

}
