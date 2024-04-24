package ej_2;

import java.util.List;

import us.lsi.common.List2;

public class SolucionCesta {
	//aqui pondria como var, numTotalProductosSeleccionados
		//Lista de los productos, para el string
		private Integer price;
		private List<Integer> productSelect;
		
		public static SolucionCesta create(List<Integer> ls) {
			return new SolucionCesta(ls);
		}
		private SolucionCesta(List<Integer> ls) {
			price=0;
			productSelect = List2.empty();
			for (int i = 0; i < ls.size(); i++) {
				if(ls.get(i)>0) {
					price+=FactoriaCesta.getPrecio(i);
					productSelect.add(FactoriaCesta.getListaProductos().get(i).Id_prod());
				}
			}
		}
		private SolucionCesta() {
			price=0;
			productSelect= List2.empty();
		}
		
		@Override
		public String toString() {
			return " Productos Seleccionados son: " + productSelect + " Precio total de la cesta: " + price;
			
		}
		
}
