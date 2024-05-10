package ej_4.manual;

import ej_4.FactoriaEmparejamiento;

public class TestBT4manual {

	public static void main(String[] args) {
		/*
	    for (Integer id_fichero = 1; id_fichero <= 3; id_fichero++) {
	        System.out.println("\nTest BT Manual Ejercicio 4 - " +
	                id_fichero);
	        FactoriaEmparejamiento.iniDatos("ficheros/Ejercicio4DatosEntrada" +
	                id_fichero + ".txt");
	        System.out.println("\n\n>\tResultados para el test " + id_fichero
	                + "\n");
	        EmparejamientoBT.search();
	        System.out.println(EmparejamientoBT.getSolucion() + "\n");
	    }
	    */
	    FactoriaEmparejamiento.iniDatos("ficheros/Ejercicio4DatosEntrada1.txt");
	    EmparejamientoBT.search();
	    System.out.println(EmparejamientoBT.getSolucion() + "\n");
	}

}
