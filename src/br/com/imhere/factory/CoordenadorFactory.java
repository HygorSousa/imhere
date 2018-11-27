package br.com.imhere.factory;


import br.com.imhere.model.Coordenador;
import br.com.imhere.model.Usuario;

public class CoordenadorFactory {
    public static Coordenador initialize() {
        Coordenador t = new Coordenador();
        t.setUsuario(new Usuario());
        return t;
    }
}
