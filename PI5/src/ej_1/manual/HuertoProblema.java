package ej_1.manual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import ej_1.FactoriaHuertos;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public record HuertoProblema(Integer index, List<Set<Integer>> reparto, List<Integer> listaMetrosDisponible) {
	
	
	public static Integer NumVariedades = FactoriaHuertos.getNumeroVariedades();
	public static Integer NumHuertos = FactoriaHuertos.getNumeroHuertos();
	
	public static HuertoProblema initial() {
		return new HuertoProblema(0, creaConjInicial(),crealistaInicial());
	}
	public static List<Integer> crealistaInicial(){
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < NumHuertos; i++) {
			res.add(FactoriaHuertos.getMetrosDisponibleH(i));
		}
		return res;
	}
	public static List<Set<Integer>> creaConjInicial(){
		 List<Set<Integer>> listaDeConjuntos = new ArrayList<>();
        for (int i = 0; i < NumHuertos; i++) {
            Set<Integer> conjunto = new HashSet<>();
            listaDeConjuntos.add(conjunto);
        }
        return listaDeConjuntos;
	}
	public List<Integer> actions() {
		List<Integer> opciones=List2.empty();
		opciones.add(NumHuertos); 
		
		if(index < NumVariedades) { 
			
			for (int i = 0; i < NumHuertos; i++) {
				
				if (listaMetrosDisponible.get(i) >= FactoriaHuertos.getMetrosRequeridosS(index)) {
					
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
	public static boolean goalHasSolution(HuertoProblema prob) {
		return prob.index == FactoriaHuertos.getNumeroVariedades();
	}
	public Double heuristic(HuertoProblema v1) {
		int numVariedadesRestantes = FactoriaHuertos.getNumeroVariedades() - v1.index();
	    int numHuertosConEspacio = 0;

	    // Verificar para cada variedad restante
	    for (int i = v1.index(); i < FactoriaHuertos.getNumeroVariedades(); i++) {
	        int metrosRequeridos = FactoriaHuertos.getMetrosRequeridosS(i);
	        boolean puedePlantarse = false;

	        // Verificar si hay algún huerto disponible para plantar la variedad actual
	        for (int j = 0; j < v1.listaMetrosDisponible().size(); j++) {
	            if (v1.listaMetrosDisponible().get(j) >= metrosRequeridos) {
	                boolean compatible = true;

	                // Verificar incompatibilidades con las variedades ya plantadas en el huerto
	                for (Integer variedad : v1.reparto().get(j)) {
	                    if (FactoriaHuertos.esIncompatible(variedad, i) == 1) {
	                        compatible = false;
	                        break;
	                    }
	                }

	                if (compatible) {
	                    puedePlantarse = true;
	                    break;
	                }
	            }
	        }

	        if (puedePlantarse) {
	            numHuertosConEspacio++;
	        }
	    }

	    // Devolver el número total de variedades que aún pueden ser plantadas en el huerto representado por v1
	    return (double) (numHuertosConEspacio + numVariedadesRestantes);
	}
	
}
