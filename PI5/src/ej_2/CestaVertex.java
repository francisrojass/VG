package ej_2;

import java.util.List;
import java.util.Set;

import us.lsi.graphs.virtual.VirtualVertex;

public record CestaVertex(Integer index,Set<Integer> categoriasPorCubrir, List<Integer> presupuestoRestante, Integer acumValoracion) 
	implements VirtualVertex<CestaVertex, CestaEdge, Integer> {
	
	public static CestaVertex of(Integer i, Set<Integer> c, List<Integer> p, Integer v) {
		return new CestaVertex(i, c, p, v);
	}
	@Override
	public List<Integer> actions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CestaVertex neighbor(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CestaEdge edge(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

}
