package br.com.imhere.factory;


import br.com.imhere.model.Professor;
import br.com.imhere.model.Usuario;

public class ProfessorFactory {
    public static Professor initialize() {
        Professor t = new Professor();
        t.setUsuario(new Usuario());
        return t;
    }
}
