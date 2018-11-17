package br.com.imhere.factory;


import br.com.imhere.model.Disciplina;

public class AtividadeFactory {
    public static Disciplina initialize() {
        Disciplina t = new Disciplina();
        return t;
    }
}
