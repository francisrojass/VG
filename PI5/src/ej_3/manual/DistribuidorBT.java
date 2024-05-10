package ej_3.manual;

import ej_3.SolucionDistribuidor;

public class DistribuidorBT {
	 private static Double mejorValor;
	    private static DistribuidorState estado;
	    private static SolucionDistribuidor solucion;

	    public static void search() {
	        solucion = null;
	        mejorValor = Double.MAX_VALUE; // Estamos minimizando
	        estado = DistribuidorState.initial();
	        bt_search();
	    }

	    private static void bt_search() {
	        if (estado.esSolucion()) {
	            Double valorObtenido = estado.acumulado;
	            if (valorObtenido < mejorValor) { // Estamos minimizando
	                mejorValor = valorObtenido;
	                solucion = estado.getSolucion();
	            }
	        } else if (!estado.esTerminal()) {
	            for (Integer a : estado.actual.actions()) {
	                if (estado.cota(a) < mejorValor) { // Estamos minimizando
	                    estado.forward(a);
	                    bt_search();
	                    estado.back();
	                }
	            }
	        }
	    }

	    public static SolucionDistribuidor getSolucion() {
	        return solucion;
	    }
}
