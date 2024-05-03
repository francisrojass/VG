package ej_2.manual;


import ej_2.FactoriaCesta;

public class CestaTest {

	public static void main(String[] args) {
		String filename = "ficheros/Ejercicio2DatosEntrada3.txt";
		FactoriaCesta.iniDatos(filename);
		System.out.println(CestaPD.search());
	}

}
