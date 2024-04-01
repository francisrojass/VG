package ej_1;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class FactoriaHuertos {

	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio1DatosEntrada1.txt");
		
    }
	
	public static List<Huerto> listaHuertos;
	public static List<Variedad> listaVariedad;
	
	public static Integer getMetrosDisponibleH(Integer j){
		return listaHuertos.get(j).metros();
        
	}
	public static Integer getMetrosRequeridosS(Integer i){
		return listaVariedad.get(i).metrosrequeridos();
	}
	
	public static List<Variedad> getVariedades(){
		return listaVariedad;
	}
	public static List<Huerto> getHuertos(){
		return listaHuertos;
	}
	/*
	 * Devuelve el numero de huertos.
	 */
	public static Integer getNumeroHuertos(){
		return listaHuertos.size();
	}
	/*
	 * Devuelve el numero de variedades.
	 */
	public static Integer getNumeroVariedades() {
		return listaVariedad.size();
	}
	/*
	 * Dado dos variedades te devuelve 1 si es incompatible y 0 si son compatible.
	 */
	public static Integer esIncompatible(Integer v1, Integer v2) {
		String nameV1 = listaVariedad.get(v1).nombre();
		List<String> incompatibilidadesV2 = listaVariedad.get(v2).variedadesIncompatibles();
		if (incompatibilidadesV2.contains(nameV1)) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public record Huerto(String nombre, Integer metros) {
		
		public static List<Huerto> parsear(String entrada) {
	        List<Huerto> huertos = new ArrayList<>();
	        String[] lineas = entrada.split("\n");
	        for (String linea : lineas) {
	            if (linea.startsWith("H")) {
	                String[] partes = linea.split(":|=|;");
	                String nombre = partes[0];
	                int metrosDisponibles = Integer.parseInt(partes[2]);
	                Huerto huerto = new Huerto(nombre, metrosDisponibles);
	                huertos.add(huerto);
	            }
	        }
	        return huertos;
	    }
		
		public String toString() {		
			return String.format("%s: %s\n", nombre, metros);
		}
	}
	public record Variedad(String nombre, Integer metrosrequeridos, List<String> variedadesIncompatibles) {
		/*
		 * la lista de incompatibilidades son de tipo [1,2,..] incomp de la variedad que tenga esta lista de con la verdura 1 y con la verdura 2...
		 */
		 public static List<Variedad> parsear(String entrada) {
		        List<Variedad> variedades = new ArrayList<>();
		        String[] lineas = entrada.split("\n");
		        
		        for (String linea : lineas) {
		            if (linea.startsWith("V")) {
		            	
		                String[] partes = linea.split("->|;|=|;");
		                
		                String nombre = partes[0].trim();
		                int metrosRequeridos = Integer.parseInt(partes[2].trim());
		                String[] incompatiblesArray = partes[4].split(",");
		                
		                List<String> incompatibles = new ArrayList<>();
		                
		                for (String incomp : incompatiblesArray) {
		                    incompatibles.add(incomp.trim());
		                }
		                Variedad variedad = new Variedad(nombre, metrosRequeridos, incompatibles);
		                variedades.add(variedad);
		            }
		        }
		        return variedades;
		    }
		 public String toString() {		
				return String.format("%s: metros requeridos = %s, incompatibilidades = %s\n", nombre, metrosrequeridos, variedadesIncompatibles);
			}
		 
	}
	
	public static void iniDatos(String fichero) {
		List<String> lineas = Files2.linesFromFile(fichero);
		listaHuertos = List2.empty();
		listaVariedad = List2.empty();
		for(String linea:lineas) {
			if(linea.contains("H")&& !linea.contains("//")) {
				listaHuertos.addAll(Huerto.parsear(linea));
			}else if (linea.contains("V")&& !linea.contains("//")) {
				listaVariedad.addAll(Variedad.parsear(linea));
			}
		}
		toConsole();
	}
	
	public static void toConsole() {
		String2.toConsole("\nHuertos: \n" + listaHuertos);
		String2.toConsole("\nVariedades: \n" + listaVariedad + "\n");
	}

}
