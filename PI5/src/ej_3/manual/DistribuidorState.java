package ej_3.manual;

import java.util.List;

import ej_3.FactoriaDistribuidor;
import ej_3.SolucionDistribuidor;
import us.lsi.common.List2;

public class DistribuidorState {
    DistribuidorProblema actual;
    Double acumulado;
    List<Integer> acciones;
    List<DistribuidorProblema> anteriores;

    private DistribuidorState(DistribuidorProblema p, Double a,
            List<Integer> ls1, List<DistribuidorProblema> ls2) {
        actual = p;
        acumulado = a;
        acciones = ls1;
        anteriores = ls2;
    }

    public static DistribuidorState initial() {
        DistribuidorProblema pi = DistribuidorProblema.initial();
        return of(pi, 0., List2.empty(), List2.empty());
    }

    public static DistribuidorState of(DistribuidorProblema prob, Double acum, List<Integer> lsa,
            List<DistribuidorProblema> lsp) {
        return new DistribuidorState(prob, acum, lsa, lsp);
    }

    public void forward(Integer a) {
        acumulado += a *
        		FactoriaDistribuidor.getAlmacenamientoCoste(actual.index() / FactoriaDistribuidor.getNumDestinos(),
                        actual.index() % FactoriaDistribuidor.getNumDestinos());
        acciones.add(a);
        anteriores.add(actual);
        actual = actual.neighbor(a);
    }

    public void back() {
        int last = acciones.size() - 1;
        var prob_ant = anteriores.get(last);
        acumulado -= acciones.get(last) * FactoriaDistribuidor.getAlmacenamientoCoste(
                prob_ant.index() / FactoriaDistribuidor.getNumDestinos(),
                prob_ant.index() % FactoriaDistribuidor.getNumDestinos());
        acciones.remove(last);
        anteriores.remove(last);
        actual = prob_ant;
    }

    public List<Integer> alternativas() {
        return actual.actions();
    }

    public Double cota(Integer a) {
        Double weight = a * FactoriaDistribuidor.getAlmacenamientoCoste(
                actual.index() / FactoriaDistribuidor.getNumDestinos(),
                actual.index() % FactoriaDistribuidor.getNumDestinos()) * 1.;
        return acumulado + weight + actual.neighbor(a).heuristic();
    }
    public Boolean esSolucion() {
        return actual.demandasRestantes().stream().allMatch(x -> x <= 0);
    }

    public boolean esTerminal() {
        return actual.index() == FactoriaDistribuidor.getNumDestinos() * FactoriaDistribuidor.getNumProductos();
    }

    public SolucionDistribuidor getSolucion() {
        return SolucionDistribuidor.of_Range(acciones);
    }
}
