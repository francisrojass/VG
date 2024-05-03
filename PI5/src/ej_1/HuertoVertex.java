package ej_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;


import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.graphs.virtual.VirtualVertex;

public record HuertoVertex(Integer index, List<Set<Integer>> reparto, List<Integer> listaMetrosDisponible) 
		implements VirtualVertex<HuertoVertex, HuertoEdge, Integer> {// el ultimo parametro de aqui es el tipo del action, se puede cambiar si nos conviene
	
	/*
	 * listaMetrosDisponibles [100,50,75] en el huerto 0 quedan 100m, en el huerto1 quedan 50m,...
	 * ConjuntoReparto lista de conjuntos de enteros [{1,2}, {3,5}, {4}, {}] en el huerto 0 esta plantada 1,2,...etc
	 */
	
	public static Integer NumVariedades = FactoriaHuertos.getNumeroVariedades();
	public static Integer NumHuertos = FactoriaHuertos.getNumeroHuertos();
	
	public static HuertoVertex of(Integer i, List<Set<Integer>> r, List<Integer> l ) {
		return new HuertoVertex(i, r, l);
	}
	
	public static HuertoVertex initial() {
		/*
		 * 		Esta funcion le da el vertice de salida a la interfaz grafo
		 * le damos un indice y los atributos iniciales para trabajar el problema.
		 */
		return of(0, creaConjInicial(), crealistaInicial());
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
	
	public static Predicate<HuertoVertex> goal() {
		/*
		 * 		Le damos un criterio de parada, si el index es igual al numero de variedades, 
		 * tiene que parar ya que se habrá hecho la decision con todas las variedades, 
		 * siempre y cuando el indice inicial sea 0.
		 * 
		 */
		return v -> v.index() == NumVariedades;
	}

	
	public static Predicate<HuertoVertex> goalHasSolution() {
	    return v -> {
	        // Verificar para cada huerto
	        for (int i = 0; i < v.reparto().size(); i++) {
	            Set<Integer> variedadesEnHuerto = v.reparto().get(i);

	            // Verificar incompatibilidades
	            for (Integer variedad : variedadesEnHuerto) {
	                for (Integer otraVariedad : variedadesEnHuerto) {
	                    if (variedad != otraVariedad && FactoriaHuertos.esIncompatible(variedad, otraVariedad) == 1) {
	                        return false; // Hay incompatibilidad en el mismo huerto
	                    }
	                }
	            }

	            // Verificar suma de metros requeridos
	            int metrosRequeridos = variedadesEnHuerto.stream()
	                .mapToInt(variedad -> FactoriaHuertos.getMetrosRequeridosS(variedad))
	                .sum();
	            if (metrosRequeridos > FactoriaHuertos.getMetrosDisponibleH(i)) {
	                return false; // La suma de metros requeridos supera los metros disponibles
	            }
	            // Verificar que cada variedad esté plantada en un solo huerto
	            for (int j = i + 1; j < v.reparto().size(); j++) {
	                Set<Integer> otrasVariedadesEnOtroHuerto = v.reparto().get(j);
	                for (Integer variedad : variedadesEnHuerto) {
	                    if (otrasVariedadesEnOtroHuerto.contains(variedad)) {
	                        return false; // La variedad está plantada en más de un huerto
	                    }
	                }
	            }
	        }
	        return true; // Todas las restricciones se cumplen
	    };
	}

	public static Integer mejorOpcion(Integer i, List<Set<Integer>> r, List<Integer> l) {
		return IntStream.range(0, r.size())
				.map(id -> r.get(id).size())
				.sum();
	}
	@Override
	public List<Integer> actions() {
		/*
		 * 		Decirle al arbol cuales son las alternativas de aristas para salir 
		 * del vertice que estoy, en este caso es la siguiente variedad si se planta o no.
		 * las opciones serian los numero de huerto que tenemos disponible, si se elige uno, 
		 * escojo el indice del huerto, si no, huertos.size()+1, esto indica que no se planta
		 */
		List<Integer> opciones=List2.empty();
		opciones.add(NumHuertos); //opcion que indica que la variedad no se planta
		
		if(index < NumVariedades) { //Comprobacion que no estamos en la ultima variedad, si no devolver una lista vacia
			/*
			 * 			En este caso se puede plantear de la siguiente manera, tenemos la variable reparto,
			 *  que es la que nos dice las variedades que estan plantadas en cada huerto, vamos a recorrer 
			 *  reparto para saber en los huertos que estan plantadas las variedades ya plantadas, si en un huerto 
			 *  hay una incompatible con la variedad de mi vertice actual, ese huerto se descartara como opcion, 
			 *  por lo tanto quedaran los huertos restantes.
			 *  
			 *  		Despues tenemos que comprobar si hay metros disponibles, cogeremos de la variedad del vertice 
			 *  actual los metros requeridos, y comprobaremos si en los huertos que tengo como opcion se puede plantar, 
			 *  en caso de que se plante en un huerto, habra que actualizar la lista de metros disponible despues de 
			 *  plantar la variedad en el huerto seleccionado plantado (Este ultimo paso se hace en neighbor).
			 */
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
		//System.out.println(opciones);
		return opciones;
	}

	@Override
	public HuertoVertex neighbor(Integer a) {
	    List<Set<Integer>> s = List2.empty();
	    
	    for (int i = 0; i < NumHuertos; i++) {
			s.add(Set2.copy(reparto.get(i)));
		}
	    
	    List<Integer> newMetrosDisponibles = List2.copy(listaMetrosDisponible);
	    //System.out.println("antiguo reparto: "+s);
	    
	    if (a < NumHuertos) { // Si a < Número de huertos, se planta una variedad en el huerto indicado por a
	        int huertoSeleccionado = a;
	        int metrosRequeridos = FactoriaHuertos.getMetrosRequeridosS(index);
	        
	        // Verificar si hay suficientes metros disponibles en el huerto seleccionado
	        if (newMetrosDisponibles.get(huertoSeleccionado) >= metrosRequeridos) {
	        	
	            // Actualizar el conjunto de variedades plantadas en el huerto seleccionado
	        	// esto creo que se puede hacer con List2.setElement(hueco, indice, huecoAdar)
	            s.get(huertoSeleccionado).add(index);
	            
	            
	            // Actualizar los metros disponibles en el huerto seleccionado después de plantar la variedad
	            newMetrosDisponibles.set(huertoSeleccionado, newMetrosDisponibles.get(huertoSeleccionado) - metrosRequeridos);
	        }
	    }
	    
	    //System.out.println("nuevo reparto: "+s);
	    // Devolver un nuevo vértice con el índice incrementado y las propiedades actualizadas
	    return of(index + 1, s, newMetrosDisponibles);
	}
	@Override
	public HuertoEdge edge(Integer a) {
		/*
		 * 		Normalmente aqui hay que devolver algo mas si tengo muchas 
		 * propiedades individuales. mirarlo con detenimiento.
		 */
		return HuertoEdge.of(this, neighbor(a), a);
	}

}
