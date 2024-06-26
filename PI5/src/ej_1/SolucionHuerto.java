package ej_1;

import java.util.ArrayList;
import java.util.List;


public class SolucionHuerto {
	List<Integer> solucion;
	long NumeroVariedades;
	long NumVariedadesPlantadas;
	
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
	    for (int numero : solucion) {
            if (numero != FactoriaHuertos.getNumeroHuertos()) {
                NumVariedadesPlantadas++;
            }
        }
	}

	private SolucionHuerto() {
		solucion = new ArrayList<Integer>();
	}

	@Override
	public String toString() {
		return "Lista: "+solucion.toString()+"\nNumero de variedades plantadas: " + NumVariedadesPlantadas;
	}
}
