package ej_1.manual;

import java.util.List;
import java.util.Set;

import ej_1.FactoriaHuertos;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public record HuertoProblema(Integer index, List<Set<Integer>> reparto, List<Integer> listaMetrosDisponible) {
	public static Integer NumVariedades = FactoriaHuertos.getNumeroVariedades();
	public static Integer NumHuertos = FactoriaHuertos.getNumeroHuertos();
	
	public static HuertoProblema initial() {
		return new HuertoProblema(0, null, null);
	}
	
	public List<Integer> actions() {
		List<Integer> opciones=List2.empty();
		opciones.add(NumHuertos); 
		if(index < NumVariedades) { 
			for (int i = 0; i < NumHuertos; i++) {	
				if (listaMetrosDisponible.get(i) > FactoriaHuertos.getMetrosRequeridosS(index)) {
					if (reparto.get(i).isEmpty()) {
						opciones.add(i);	
					} else {
						for (int z = 0; z < reparto.size(); z++) {
							Set<Integer> conjunto = reparto.get(z);
							
						    for (Integer entero : conjunto) {
						    	
						        if(FactoriaHuertos.esIncompatible(entero, index) == 0) {
						        	opciones.add(i);
						        }   
						    }
						}
					}
					
				}
			}
			
		}
		return opciones;
	}

	public HuertoProblema neighbor(Integer a) {
	    List<Set<Integer>> s = List2.empty();
	    
	    for (int i = 0; i < NumHuertos; i++) {
			s.add(Set2.copy(reparto.get(i)));
		}
	    
	    List<Integer> newMetrosDisponibles = List2.copy(listaMetrosDisponible);
	    
	    if (a < NumHuertos) { 
	        int huertoSeleccionado = a;
	        int metrosRequeridos = FactoriaHuertos.getMetrosRequeridosS(index);
	        
	        if (newMetrosDisponibles.get(huertoSeleccionado) >= metrosRequeridos) {
	        	
	            s.get(huertoSeleccionado).add(index);
	            newMetrosDisponibles.set(huertoSeleccionado, newMetrosDisponibles.get(huertoSeleccionado) - metrosRequeridos);
	        }
	    }
	    
	    return new HuertoProblema(index + 1, s, newMetrosDisponibles);
	}
}
