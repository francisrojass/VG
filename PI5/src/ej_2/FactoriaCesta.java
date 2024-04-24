package ej_2;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.String2;

public class FactoriaCesta {

	public static Integer Presupuesto;
	public static List<Producto> ListaProductos;
	public static Integer id;
	public static Integer precio;
	public static Integer categoria;
	public static Integer valoracion;
	
	public static Integer getCategoria(Integer i, Integer j){
		if (ListaProductos.get(i).Categoria.equals(j)) {
			return 1;
		} else {
			return 0;
		}
	}
	public static Integer getCategoriaAG(Integer i) {
		return ListaProductos.get(i).Categoria;
	}
	
	public static Integer getPrecioDeCategoria(Integer i, Integer j) {
		if (ListaProductos.get(i).Categoria.equals(j)) {
			return ListaProductos.get(i).Precio;
		} else {
			return 0;
		}
	}
	
	public static Integer getNumProductos() {
		return ListaProductos.size();
	}
	public static Integer getNumCategorias() {
		Set<Integer> conjunto = new HashSet<Integer>();
		for(Producto p: ListaProductos) {
			conjunto.add(p.Categoria);
		}
		return conjunto.size();
	}
	public static List<Producto> getListaProductos() {
		return ListaProductos;
	}
	public static Integer getPresupuesto() {
		return Presupuesto;
	}
	public static Integer getValoracion(Integer i) {
		return ListaProductos.get(i).Valoracion;
	}
	public static Integer getPrecio(Integer i) {
		return ListaProductos.get(i).Precio;
	}
	public static Integer parseaPresupuesto(String entrada) {
		String[] linea = entrada.split("=");
		Presupuesto = Integer.parseInt(linea[1].trim());
		return Presupuesto;
	}
	public record Producto(Integer Id_prod,Integer Precio,Integer Categoria,Integer Valoracion) {
		
		public static Producto parseaProducto(String entrada) {
			String[] linea = entrada.split(":|:|:");
			id = Integer.parseInt(linea[0]);
			precio = Integer.parseInt(linea[1]);
			categoria= Integer.parseInt(linea[2]);
			valoracion= Integer.parseInt(linea[3]);
			return new Producto(id, precio, categoria, valoracion);
		}
		public String toString() {		
			return String.format("%s: %s: %s: %s\n", Id_prod, Precio,Categoria,Valoracion);
		}
	}
	public static void iniDatos(String File) {
		List<String> lineas = Files2.linesFromFile(File);
		Presupuesto = 0;
		ListaProductos = List2.empty();
		for(String linea:lineas) {
			if(linea.contains("Presupuesto") && !linea.contains("//")) {
				Presupuesto = parseaPresupuesto(linea);
				
			}else if(!linea.contains("//")) {
				ListaProductos.add(Producto.parseaProducto(linea));
			}
		}
		toConsole();
	}
	public static void toConsole() {
		String2.toConsole("\nPresupuesto: \n" + Presupuesto);
		String2.toConsole("\nLista Productos: \n" + ListaProductos);
	}
	
	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio2DatosEntrada1.txt");
	}
	
	public static int getPrecioMinimoCategoria(int categoria2) {
		Optional<Producto> res = ListaProductos.stream()
                .filter(producto -> producto.Categoria == categoria) // Filtrar por categoría
                .min(Comparator.comparingInt(Producto::Precio));
		return res.get().Precio;
	}


}
