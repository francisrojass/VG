package ej_1.manual;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import ej_1.FactoriaHuertos;
import ej_1.SolucionHuerto;
import us.lsi.common.List2;
import us.lsi.common.Map2;

public class Huerto_PD {
	
	public static record Sph(Integer a,Integer weight) implements Comparable<Sph> {
		public static Sph of(Integer a, Integer weight) {
			return new Sph(a,weight);
		}
		public int compareTo(Sph sp) {
			return this.weight.compareTo(sp.weight);
		}
	}
	public static Map<HuertoProblema, Sph> memory;
	public static Integer mejorValor = Integer.MIN_VALUE;
	
	public static SolucionHuerto search() {
		memory = Map2.empty();
		mejorValor = Integer.MIN_VALUE;
		
		pdr(HuertoProblema.initial(), 0);
		return getSolucion();
	}

	private static Sph pdr(HuertoProblema prob, int acum) {
		Boolean esTerminal = prob.index() == FactoriaHuertos.getNumeroVariedades();
		Boolean esSolucion = true;
		
		
		Sph r = null;
		if(memory.containsKey(prob)) {
			r = memory.get(prob);
			
		} else if(esTerminal && esSolucion) {
			r = Sph.of(null, 0);
			memory.put(prob, r);
			if (acum > mejorValor) {
				mejorValor = acum;
			}
			
		}else {
			List<Sph> ls = List2.empty();
			for(Integer a: prob.actions()) {
				Double cota = acotar(acum,prob, a); 
				if (cota <= mejorValor) {
					continue;
				}
				HuertoProblema vecino = prob.neighbor(a);
				Integer weight = a != FactoriaHuertos.getNumeroHuertos() ? 1 :0;
				
				Sph s = pdr(vecino, acum + weight);
				if (s != null) {
					Sph amp = Sph.of(a, s.weight() + weight);
					ls.add(amp);
				}
				
			}
			r = ls.stream().max(Comparator.naturalOrder()).orElse(null);
			if (r!=null) {
				memory.put(prob, r);				
			}
		}
		return r;
	}
	private static Double acotar(Integer acum, HuertoProblema p, Integer a) {
		Integer pesoArista = a != FactoriaHuertos.getNumeroHuertos() ? 1 :0;
		return acum + pesoArista + p.neighbor(a).heuristic(p);
	}
	
	public static SolucionHuerto getSolucion() {
		List<Integer> actions = List2.empty();
		Sph sph = memory.get(HuertoProblema.initial());
		HuertoProblema prob = HuertoProblema.initial();
		while (sph!=null && sph.a!=null) {
			actions.add(sph.a);
			prob = prob.neighbor(sph.a);
			
		}
		return SolucionHuerto.of(actions);
		

	}
	
}
