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
	
	public static void main(String[] args) {
		memory = Map2.empty();
		mejorValor = Integer.MIN_VALUE;
		
		pdr(HuertoProblema.initial(), 0, memory);
		getSolucion();
	}
	private static Sph pdr(HuertoProblema prob, int acum, Map<HuertoProblema, Sph> memoria) {
		Sph r = null;
		if(memory.containsKey(prob)) {
			r = memory.get(prob);
			
		} else if(HuertoProblema.goalHasSolution(prob)) {
			r = Sph.of(null, 0);
			memory.put(prob, r);
			if (acum > mejorValor) {
				mejorValor = acum;
			}
			
		}else {
			List<Sph> ls = List2.empty();
			for(Integer a:prob.actions()) {
				if (acum + cota(prob, a) > mejorValor) {
					Sph sh = pdr(prob.neighbor(a), acum + pesoArista(prob,a), memoria);
					if (sh!=null) {
						r = Sph.of(a, pesoArista(prob, a) + sh.weight);
						ls.add(r);
					}
				}
				
			}
			if (r!=null) {
				r=ls.stream().max(Comparator.naturalOrder()).get();
				memory.put(prob, r);
				
			}
		}
		return r;
	}
	private static double cota(HuertoProblema prob, Integer a) {
		return pesoArista(prob, a) + HuertoProblema.heuristic(prob.neighbor(a));
	}
	private static Integer pesoArista(HuertoProblema prob, Integer a) {
		return a == FactoriaHuertos.getNumeroVariedades()?0:1;
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
