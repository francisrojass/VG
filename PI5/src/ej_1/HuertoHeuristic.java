package ej_1;

import java.util.function.Predicate;

public class HuertoHeuristic {
	/*
	 * 		Los problemas se pueden resolver por heuristica, pero si se la das(hay que darsela), optimiza muchisimo el grafo 
	 * virtual, cada problema tiene la suya y es una estimacion que se ejecuta antes de un movimiento.
	 * 
	 * 		Recorremos los indice que son las variedades, las alternativas son los huerto donde se plantan la verdura
	 * el vertice del grafo sera la variedad y las alternativa en las aristas el huerto donde se plantaria si se coge ese camino.
	 * 
	 * 		Heuristica, se traduce a un conteo, el numero de verduras o de verduras aun no visitadas, tales que para cada una de ellas 
	 * existe al menos un huerto con metros suficientes para su plantacion , 
	 * de las variedades que nos quedan por visitar, vamos a tener suerte y vamos plantar cuatro mas, para continuar, 
	 * posiblemente sean menos, pero hay que maximizar y ser optimista
	 * 
	 * 		En este ejercicio se podria mirar en la estimacion de si se puede plantar mas variedades en ese camino, mirando la 
	 * incompatibilades y metros disponibles.
	 * 
	 * 		Comprobar si las variedades que me quedan ver si se pueden plantar en algun huerto, sabiendo 
	 * las que tengo ya plantadas, ver si es incompatible o no me queda mas espacio
	 */
	
	public static Double heuristic(HuertoVertex v1, Predicate<HuertoVertex> goal, HuertoVertex v2) {
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
