package ej_1.manual;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import ej_1.FactoriaHuertos;
import ej_1.SolucionHuerto;
import us.lsi.common.List2;
import us.lsi.common.Map2;

public class Huerto_PD {
	
	public static record SpH(Integer coste, Integer alternativa) implements Comparable<SpH> {
		public int compareTo(SpH o) {
			return coste.compareTo(o.coste);
		}
	}
	
	public static Map<HuertoProblema, SpH> memory;
	public static Integer mejorValor = Integer.MAX_VALUE;
	
	public static SpH search() {
		memory = Map2.empty();
		HuertoProblema inicial = HuertoProblema.initial();
		
		SpH resultado = pdr_search(inicial, memory, 0);
		return resultado;
	}
	
	public static SpH pdr_search(HuertoProblema problema, Map<HuertoProblema, SpH> memory, Integer valorAcum) {
		
		Boolean esTerminal = problema.index().equals(FactoriaHuertos.getNumeroVariedades());
		Boolean esSolucion = true; //este es el goalHasSolutions, hacerlo
		
		if (memory.containsKey(problema))
		{
			return memory.get(problema);
		}
		else if(esTerminal && esSolucion)
		{
			/*
			 * Casos Bases del problema
			 * Por ejemplo comprobar si hemos llegado al final del array
			 */
			SpH solucion = new SpH(0, null);
			memory.put(problema, solucion);
			return solucion;
		}
		else 
		{
			List<SpH> soluciones=List2.empty();
			for(Integer action: problema.actions()) {
				/*
				 * El acum es lo que lleve acumlao mas lo de ahora en cada problema es diferente
				 */
				
				SpH res = pdr_search(problema.neighbor(action), memory, valorAcum);
				if (res!=null)
					soluciones.add(res);
			}
			SpH solucion = soluciones.stream().min(Comparator.naturalOrder()).orElse(null);
			if (solucion!=null) {
				memory.put(problema, solucion);
			}
			return solucion;
		}
	}
	
	public static SolucionHuerto getSolucion() {
		List<Integer> actions = List2.empty();
		SpH sph = memory.get(HuertoProblema.initial());
		HuertoProblema prob = HuertoProblema.initial();
		while (sph!=null && sph.alternativa!=null) {
			actions.add(sph.alternativa);
			prob = prob.neighbor(sph.alternativa);
			
		}
		return SolucionHuerto.of(actions);
		

	}
	
}
