package ej_1.manual;

import ej_1.FactoriaHuertos;

public class TestHuertoPDmanual {

	public static void main(String[] args) {
		String filename = "ficheros/Ejercicio1DatosEntrada1.txt";
		FactoriaHuertos.iniDatos(filename);
		System.out.println(Huerto_PD.search());
		
	}

}
