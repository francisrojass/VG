package ej_4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

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
		return of(0, creaConj(), 0);
	}
	public static Set<Integer> creaConj(){
		Set<Integer> res = Set2.empty();
		for (int i = 1; i < NumPersonas; i++) {
			res.add(i);
		}
		return res;
	}
	public static Predicate<EmparejamientoVertex> goal(){
		return v -> v.index.equals(NumPersonas-1);
	}
	public static Predicate<EmparejamientoVertex> goalHasSolution(){
		return v -> v.restante.isEmpty();
		//return v -> true;
	}
	
	@Override
	public List<Integer> actions() {
		List<Integer> opciones = List2.empty();
		
		if(index >= NumPersonas) return opciones;
		
		if (index%2 != 0) {
			
			for (Integer persona: restante) {
				opciones.add(persona);
			}
			//System.out.println(opciones+" " + "ultima: " + ultima+ " index: "+ index + " Restante: " +restante);
			return opciones;
			
		} else {
			List<String> izq = new ArrayList<String>();
			
			if(index==0) {
				izq = FactoriaEmparejamiento.getIdiomas(0);
			}else {
				izq = FactoriaEmparejamiento.getIdiomas(ultima);
			}
			
			for (Integer persona: restante) {
				
				List<String> der = FactoriaEmparejamiento.getIdiomas(persona);
				
				if(tienenElementoComun(izq, der)) {
					
					if(Math.abs(FactoriaEmparejamiento.getEdad(ultima) - FactoriaEmparejamiento.getEdad(persona)) <= 5) {
						
						if (!(FactoriaEmparejamiento.getNacionalidad(ultima).equals(FactoriaEmparejamiento.getNacionalidad(persona)))) {
							opciones.add(persona);
						}
					}
				}
					
						
			}
		}	
		//System.out.println(opciones+" " + "ultima: " + ultima+ " index: "+ index + " Restante: " +restante);
		return opciones;
	}

	public static boolean tienenElementoComun(List<String> lista1, List<String> lista2) {
        for (String elemento : lista1) {
            if (lista2.contains(elemento)) {
                return true;
            }
        }
        return false;
    }

	@Override
	public EmparejamientoVertex neighbor(Integer a) {
		Set<Integer> NewRestante = Set2.copy(restante);
		Integer NewUltima = ultima;
		/*
		if (index%2 == 0) {
			
			NewUltima = NumPersonas;
			
		}else {
			
			NewUltima = a;
		}
		*/
		NewUltima = a;
		NewRestante.remove(a);
		
		return of(index + 1, NewRestante, NewUltima);
	}
	@Override
	public EmparejamientoEdge edge(Integer a) {
		return EmparejamientoEdge.of(this, neighbor(a), a);
	}

}
