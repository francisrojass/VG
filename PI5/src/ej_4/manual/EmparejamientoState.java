package ej_4.manual;

import java.util.List;

import ej_4.FactoriaEmparejamiento;
import ej_4.SolucionEmparejamiento;
import us.lsi.common.List2;

public class EmparejamientoState {
	EmparejamientoProblema actual;
    Double acumulado;
    List<Integer> acciones;
    List<EmparejamientoProblema> anteriores;

    private EmparejamientoState(EmparejamientoProblema p, Double a, List<Integer> ls1,
            List<EmparejamientoProblema> ls2) {
        actual = p;
        acumulado = a;
        acciones = ls1;
        anteriores = ls2;
    }

    public static EmparejamientoState initial() {
        EmparejamientoProblema pi = EmparejamientoProblema.initial();
        return of(pi, 0., List2.empty(), List2.empty());
    }

    public static EmparejamientoState of(EmparejamientoProblema prob, Double acum, List<Integer> lsa, List<EmparejamientoProblema> lsp) {
        return new EmparejamientoState(prob, acum, lsa, lsp);
    }

    public void forward(Integer a) {
        if (actual.ultima() != FactoriaEmparejamiento.getNumeroPersonas())
            acumulado += FactoriaEmparejamiento.getAfinidad(actual.ultima(), a);
        acciones.add(a);
        anteriores.add(actual);
        actual = actual.neighbor(a);
    }

    public void back() {
        int last = acciones.size() - 1;
        var prob_ant = anteriores.get(last);
        if (prob_ant.ultima() != FactoriaEmparejamiento.getNumeroPersonas())
            acumulado -= FactoriaEmparejamiento.getAfinidad(prob_ant.ultima(),
                    acciones.get(last));
        acciones.remove(last);
        anteriores.remove(last);
        actual = prob_ant;
    }

    public List<Integer> alternativas() {
        return actual.actions();
    }

    public Double cota(Integer a) {
        Integer weight = 0;
        if (actual.ultima() != FactoriaEmparejamiento.getNumeroPersonas())
            weight = FactoriaEmparejamiento.getAfinidad(actual.ultima(), a);
        return acumulado + weight + actual.neighbor(a).heuristic();
    }

    public Boolean esSolucion() {
        return true;
    }

    public boolean esTerminal() {
        return actual.index() == FactoriaEmparejamiento.getNumeroPersonas();
    }

    public SolucionEmparejamiento getSolucion() {
        return SolucionEmparejamiento.of(acciones);
    }
}
