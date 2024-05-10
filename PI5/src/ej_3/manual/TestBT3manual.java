package ej_3.manual;

import ej_3.FactoriaDistribuidor;

public class TestBT3manual {
	public static void main(String[] args) {
        
        for (Integer i = 1; i <= 3; i++) {
            System.out.println("\nTest BT Manual Ejercicio 3 - " + i);
            FactoriaDistribuidor.iniDatos("ficheros/Ejercicio3DatosEntrada" + i + ".txt");
            System.out.println("\n\n>\tResultados para el test " + i + "\n");
            DistribuidorBT.search();
            System.out.println(DistribuidorBT.getSolucion() + "\n");
        }
        
        
    }
}
