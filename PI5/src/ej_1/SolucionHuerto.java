package ej_1;

import java.util.ArrayList;
import java.util.List;


public class SolucionHuerto {
	List<Integer> solucion;
	long NumeroVariedades;
	long NumMetros;
	
	public static SolucionHuerto of(List<Integer> ls) {
		return new SolucionHuerto(ls);
	}
	
	public static SolucionHuerto empty() {
		return new SolucionHuerto();
	}
	
	private SolucionHuerto(List<Integer> ls) {
	    solucion = new ArrayList<Integer>();
	    for (Integer valor : ls) {
	        solucion.add(valor);
	    }
	    NumMetros = solucion.stream()
	    		.filter(i->!(ls.get(i) == FactoriaHuertos.getNumeroHuertos()+1))
	    		.mapToInt(i-> FactoriaHuertos.getMetrosRequeridosS(ls.get(i)))
	    		.sum();
	    		
	}

	private SolucionHuerto() {
		solucion = new ArrayList<Integer>();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Lista: "+solucion.toString()+"\nNumero de metros plantados: " + NumMetros;
	}
}
