package br.com.imhere.factory;


import br.com.imhere.model.Aluno;
import br.com.imhere.model.Usuario;

public class AlunoFactory {
    public static Aluno initialize() {
        Aluno t = new Aluno();
        t.setUsuario(new Usuario());
        return t;
    }
}
