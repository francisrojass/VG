package ej_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import ej_2.FactoriaCesta.Producto;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record CestaVertex(Integer index,Set<Integer> categoriasPorCubrir, List<Integer> presupuestoRestante, Integer acumValoracion) 
	implements VirtualVertex<CestaVertex, CestaEdge, Integer> {
	
	public static CestaVertex of(Integer i, Set<Integer> c, List<Integer> p, Integer v) {
		return new CestaVertex(i, c, p, v);
	}
	public static CestaVertex initial() {
		return of(0, CreaConjunto(), creaLista(), 0);
	}
	private static List<Integer> creaLista() {
		List<Integer> res=new ArrayList<>();
		for (int i = 0; i < FactoriaCesta.getNumCategorias(); i++) {
			res.add(FactoriaCesta.getPresupuesto());
		}
		return res;
	}
	private static Set<Integer> CreaConjunto() {
		Set<Integer> res=new HashSet<>();
		for (int i = 0; i < FactoriaCesta.getNumCategorias(); i++) {
			res.add(i);
		}
		return res;
	}
	public static Predicate<CestaVertex> goal(){
		return v->v.index == FactoriaCesta.getNumProductos();
	}
	
	public static Predicate<CestaVertex> goalHasSolution(){
		return v->v.categoriasPorCubrir.isEmpty();
	}
	
	@Override
	public List<Integer> actions() {
		List<Integer> opciones=List2.empty();
		opciones.add(0);
		if(index < FactoriaCesta.getNumProductos()) {
			Integer categoria = FactoriaCesta.getCategoriaAG(index);
			if (categoriasPorCubrir.contains(categoria)) {
				if(FactoriaCesta.getPrecio(index) < presupuestoRestante.get(categoria)) {
					opciones.add(1);
				}
			}
			
		}
		return opciones;
	}

	@Override
	public CestaVertex neighbor(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CestaEdge edge(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

}
