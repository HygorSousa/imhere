package br.com.imhere.factory;


import br.com.imhere.model.Curso;

public class CursoFactory {
    public static Curso initialize() {
        Curso t = new Curso();
        return t;
    }
}
