package br.com.imhere.factory;


import br.com.imhere.model.Coordenador;

public class CoordenadorFactory {
    public static Coordenador initialize() {
        Coordenador t = new Coordenador();
        return t;
    }
}
