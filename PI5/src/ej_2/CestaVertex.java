package ej_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.graphs.virtual.VirtualVertex;

public record CestaVertex(Integer index,Set<Integer> categoriasPorCubrir, List<Integer> presupuestoRestante, Integer acumValoracion) 
	implements VirtualVertex<CestaVertex, CestaEdge, Integer> {
	
	public static CestaVertex of(Integer i, Set<Integer> c, List<Integer> p, Integer v) {
		return new CestaVertex(i, c, p, v);
	}
	public static CestaVertex initial() {
		return of(0, creaConjunto(), creaLista(), 0);
	}
	private static List<Integer> creaLista() {
		List<Integer> res=new ArrayList<>();
		for (int i = 0; i < FactoriaCesta.getNumCategorias(); i++) {
			res.add(FactoriaCesta.getPresupuesto());
		}
		return res;
	}
	private static Set<Integer> creaConjunto() {
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
		return v->	v.categoriasPorCubrir.size() == 0 && 
					v.acumValoracion > 0 && 
					v.presupuestoRestante.stream().allMatch(x -> x >= 0);			
	}
	
	@Override
	public List<Integer> actions() {
	    List<Integer> opciones = new ArrayList<>();
	    
	    if(index < FactoriaCesta.getNumProductos()) {
	        Integer categoria = FactoriaCesta.getCategoriaAG(index);
	        
	        if (categoriasPorCubrir.contains(categoria) && FactoriaCesta.getPrecio(index) < presupuestoRestante.get(categoria)) {
	            opciones.add(1);
	        }
	        
	        opciones.add(0); // Siempre hay la opción de no elegir el producto
	    }
	    
	    return opciones;
	}

	@Override
	public CestaVertex neighbor(Integer a) {
		Set<Integer> NewCategoriasPorCubrir = Set2.copy(categoriasPorCubrir);
		List<Integer> NewPresupuestoRestante = List2.copy(presupuestoRestante);
		Integer NewAcumValoracion = acumValoracion;
		
		//System.out.println(NewCategoriasPorCubrir);
		if (a == 1) { 
			if (NewCategoriasPorCubrir.contains(FactoriaCesta.getCategoriaAG(index))) {
				NewCategoriasPorCubrir.remove(FactoriaCesta.getCategoriaAG(index));
			}
			
			NewPresupuestoRestante.set(
					FactoriaCesta.getCategoriaAG(index), 
					NewPresupuestoRestante.get(FactoriaCesta.getCategoriaAG(index)) - FactoriaCesta.getPrecio(index));
			
			NewAcumValoracion = NewAcumValoracion + FactoriaCesta.getValoracion(index) - 3;
		}
		//System.out.println(NewPresupuestoRestante);
		return of(index + 1, NewCategoriasPorCubrir, NewPresupuestoRestante, NewAcumValoracion);
	}

	@Override
	public CestaEdge edge(Integer a) {
		return CestaEdge.of(this, neighbor(a), a);
	}

}
