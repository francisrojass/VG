package ej_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class FactoriaEmparejamiento {

public static List<Persona> ListaPersonas;
	
	public static Integer getNumeroPersonas() {
		return ListaPersonas.size();
	}
	public static Integer getEdad(Integer i) {
		return ListaPersonas.get(i).edad();
	}
	public static String getNacionalidad(Integer i) {
		return ListaPersonas.get(i).nacionalidad();
	}
	public static Integer getAfinidad(Integer i, Integer j) {
		if(i==j) return 0;
		return ListaPersonas.get(i).afinidades().get(j).intValue();
	}
	public static List<String> getIdiomas(Integer i){
		return ListaPersonas.get(i).idiomas();
	}
	public record Persona(String id, 
			Integer edad,
			List<String> idiomas,
			String nacionalidad,
			Map<Integer, Integer> afinidades) {
		public static Persona create(String entrada){
			String[] v = entrada.split("->|=|;|=|;|=|;|=|;|=|;");
			String id = v[0];
			Integer edad =Integer.parseInt(v[2]);
			List<String> idiomas = parseaIdiomas(v[4]);
			String nacionalidad = v[6];
			Map<Integer, Integer> afinidades = parseaStringMap(v[8]);
			return new Persona(id, edad, idiomas, nacionalidad, afinidades);
		}
	
	}
	private static Map<Integer, Integer> parseaStringMap(String input){
		Map<Integer, Integer> mapa = new HashMap<>();
        // Expresión regular para encontrar los pares (clave:valor)
        String regex = "\\((\\d+):(\\d+)\\)";
        // Crear el patrón de la expresión regular
        Pattern pattern = Pattern.compile(regex);
        // Crear el objeto Matcher
        Matcher matcher = pattern.matcher(input);
        // Iterar sobre las coincidencias
        while (matcher.find()) {
            // Extraer la clave y el valor de la coincidencia
            int clave = Integer.parseInt(matcher.group(1));
            int valor = Integer.parseInt(matcher.group(2));
            // Agregar el par clave-valor al mapa
            mapa.put(clave, valor);
        }
        return mapa;
	}
	private static List<String> parseaIdiomas(String cadena) {
		List<String> listaIdiomas = new ArrayList<>();
        String[] idiomasArray = cadena.replaceAll("[()]", "").split(",");
        for (String idioma : idiomasArray) {
            listaIdiomas.add(idioma);
        }
        return listaIdiomas;
	}
	public static void iniDatos(String File) {
		List<String> lineas = Files2.linesFromFile(File);
		ListaPersonas = List2.empty();
		
		for(String linea:lineas) {
			if(!linea.contains("//")) {
				ListaPersonas.add(Persona.create(linea));
			}
		}
		toConsole();
	}
	public static void toConsole() {
		String2.toConsole("\nLista de Personas: \n" + ListaPersonas);
	}
	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio4DatosEntrada1.txt");
	}

}
