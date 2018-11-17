package br.com.imhere.factory;


import br.com.imhere.model.Professor;

public class ProfessorFactory {
    public static Professor initialize() {
        Professor t = new Professor();
        return t;
    }
}
