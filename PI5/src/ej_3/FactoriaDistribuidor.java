package ej_3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class FactoriaDistribuidor {

	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio3DatosEntrada1.txt");
	}
	public static List<Destino> ListaDestino;
	public static List<Productos> ListaProductos;
	
	public static Integer getNumDestinos() {
		return ListaDestino.size();
	}
	public static Integer getNumProductos() {
		return ListaProductos.size();
	}
	public static Integer getAlmacenamientoCoste(Integer i, Integer j) {
		return ListaProductos.get(i).coste_almacenamiento.get(j);
	}
	public static Integer getCantidadDisponibleUnidades(Integer i) {
		return ListaProductos.get(i).unidades;
	}
	public static Integer getDemandaMinima(Integer j) {
		return ListaDestino.get(j).demandaMinima;
	}
	public static List<Productos> getProductos(){
		return ListaProductos;
	}
	public static List<Destino> getDestinos(){
		return ListaDestino;
	}
	public record Destino(String name, Integer demandaMinima) {
		public static Destino create(String entrada){
			String[] v = entrada.split(":|=|;");
			String n = v[0];
			Integer d = Integer.parseInt(v[2]);
			return new Destino(n, d);
		}
	}
	public record Productos(String name, Integer unidades, Map<Integer, Integer> coste_almacenamiento) {
		public static Productos create(String entrada){
			String[] v = entrada.split("->|=|;|=|;");
			String n = v[0];
			Integer d = Integer.parseInt(v[2]);
			Map<Integer, Integer> coste = parseStringAMapa(v[4]);
			return new Productos(n, d, coste);
		}
	}
	public static void iniDatos(String File) {
		List<String> lineas = Files2.linesFromFile(File);
		ListaDestino = List2.empty();
		ListaProductos = List2.empty();
		for(String linea:lineas) {
			if(linea.contains("D") && !linea.contains("//")) {
				ListaDestino.add(Destino.create(linea));
				
			}else if(!linea.contains("//")) {
				ListaProductos.add(Productos.create(linea));
			}
		}
		toConsole();
	}
	public static void toConsole() {
		String2.toConsole("\nDestinos: \n" + ListaDestino);
		String2.toConsole("\nLista de Productos: \n" + ListaProductos);
	}
	
    public static Map<Integer, Integer> parseStringAMapa(String input) {
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

}
