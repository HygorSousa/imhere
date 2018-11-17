package br.com.imhere.factory;


import br.com.imhere.model.MatrizCurricular;

public class MatrizCurricularFactory {
    public static MatrizCurricular initialize() {
        MatrizCurricular t = new MatrizCurricular();
        return t;
    }
}
