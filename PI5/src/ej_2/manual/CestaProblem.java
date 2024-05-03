package ej_2.manual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ej_2.FactoriaCesta;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public record CestaProblem(Integer index,Set<Integer> categoriasPorCubrir, List<Integer> presupuestoRestante, Integer acumValoracion) {
	public static CestaProblem of(Integer i, Set<Integer> c, List<Integer> p, Integer v) {
		return new CestaProblem(i, c, p, v);
	}
	public static CestaProblem initial() {
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
	public CestaProblem neighbor(Integer a) {
		Set<Integer> NewCategoriasPorCubrir = Set2.copy(categoriasPorCubrir);
		List<Integer> NewPresupuestoRestante = List2.copy(presupuestoRestante);
		Integer NewAcumValoracion = acumValoracion;
		
		if (a == 1) { 
			if (NewCategoriasPorCubrir.contains(FactoriaCesta.getCategoriaAG(index))) {
				NewCategoriasPorCubrir.remove(FactoriaCesta.getCategoriaAG(index));
			}
			
			NewPresupuestoRestante.set(
					FactoriaCesta.getCategoriaAG(index), 
					NewPresupuestoRestante.get(FactoriaCesta.getCategoriaAG(index)) - FactoriaCesta.getPrecio(index));
			
			NewAcumValoracion = NewAcumValoracion + FactoriaCesta.getValoracion(index) - 3;
		}
		
		return of(index + 1, NewCategoriasPorCubrir, NewPresupuestoRestante, NewAcumValoracion);
	}
}
