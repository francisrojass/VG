package ej_4.manual;

import ej_4.SolucionEmparejamiento;

public class EmparejamientoBT {
	
	  	private static Double mejorValor;
	    private static EmparejamientoState estado;
	    private static SolucionEmparejamiento solucion;

	    public static void search() {
	        solucion = null;
	        mejorValor = Double.MIN_VALUE;
	        estado = EmparejamientoState.initial();
	        bt_search();
	    }

	    private static void bt_search() {
	        if (estado.esTerminal()) {
	            Double valorObtenido = estado.acumulado;
	            if (valorObtenido > mejorValor) { 
	                mejorValor = valorObtenido;
	                solucion = estado.getSolucion();
	            }
	        } else {
	            for (Integer a : estado.actual.actions()) {
	                Double cota = estado.cota(a);
	                if (cota < mejorValor)
	                    continue;
	                estado.forward(a);
	                bt_search();
	                estado.back();
	            }
	        }
	    }

	    public static SolucionEmparejamiento getSolucion() {
	        return solucion;
	    }
}
