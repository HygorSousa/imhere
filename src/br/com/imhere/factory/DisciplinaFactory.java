package br.com.imhere.factory;


import br.com.imhere.model.Disciplina;

public class DisciplinaFactory {
    public static Disciplina initialize() {
        Disciplina t = new Disciplina();
        return t;
    }
}
