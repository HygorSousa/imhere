package br.com.imhere.factory;

import br.com.imhere.model.Atividade;

public class AtividadeFactory {
    public static Atividade initialize() {
        Atividade t = new Atividade();
        return t;
    }
}
