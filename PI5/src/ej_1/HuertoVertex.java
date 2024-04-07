package ej_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;


import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record HuertoVertex(Integer index, List<Set<Integer>> reparto, List<Integer> listaMetrosDisponible) 
		implements VirtualVertex<HuertoVertex, HuertoEdge, Integer> {// el ultimo parametro de aqui es el tipo del action, se puede cambiar si nos conviene
	
	/*
	 * listaMetrosDisponibles [100,50,75] en el huerto 0 quedan 100m, en el huerto1 quedan 50m,...
	 * ConjuntoReparto lista de conjuntos de enteros [{1,2} {3,5} {4} {}] en el huerto 0 esta plantada 1,2,...etc
	 */
	public static Integer NumVariedades;
	public static Integer NumHuertos;
	
	public static HuertoVertex of(Integer i, List<Set<Integer>> r, List<Integer> l ) {
		return new HuertoVertex(i, r, l);
	}
	
	public static HuertoVertex initial() {
		/*
		 * 		Esta funcion le da el vertice de salida a la interfaz grafo
		 * le damos un indice y los atributos iniciales para trabajar el problema.
		 */
		NumHuertos = FactoriaHuertos.getNumeroHuertos();
		return of(0, new ArrayList<Set<Integer>>(), List2.rangeList(0, NumHuertos));
	}
	
	public static Predicate<HuertoVertex> goal() {
		/*
		 * 		Le damos un criterio de parada, si el index es igual al numero de variedades, 
		 * tiene que parar ya que se habrá hecho la decision con todas las variedades, 
		 * siempre y cuando el indice inicial sea 0.
		 * 
		 */
		
		return v -> v.index() == FactoriaHuertos.getNumeroVariedades();
	}
	public static Predicate<HuertoVertex> goalHasSolution() {
		/*
		 * Este hay que darselo a AStar para decirle que es la mejor solucion, siempre son vertice finales.
		 */
		return null;	
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
		if(index<FactoriaHuertos.getNumeroHuertos()) {
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
			 *  plantar la variedad en el huerto seleccionado plantado (Este ultimo paso nose si hay que hacerlo aquí).
			 */
		}
		return null;
	}

	@Override
	public HuertoVertex neighbor(Integer a) {
		List<Set<Integer>> s = List2.copy(reparto); //Esto esta mal, hacerlo mejor, esto tiene que calcular el vertice siguiente
		s.remove(a);
		return of(index+1, s, listaMetrosDisponible);
	}

	@Override
	public HuertoEdge edge(Integer a) {
		return HuertoEdge.of(this, neighbor(a), a);
	}

}
