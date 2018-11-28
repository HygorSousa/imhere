package br.com.imhere.factory;


import br.com.imhere.model.Semestre;

public class SemestreFactory {
    public static Semestre initialize() {
        Semestre t = new Semestre();
        return t;
    }
}
