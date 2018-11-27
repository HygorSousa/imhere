package br.com.imhere.model;

public enum TipoUsuario {

    COORDENADOR(0, "Coordenador"),
    PROFESSOR(1, "Professor"),
    ALUNO(2, "Aluno");

    private int valor;
    private String label;

    TipoUsuario(int valor, String label) {
        this.valor = valor;
        this.label = label;
    }

    public int getValor() {
        return valor;
    }

    public String getLabel() {
        return label;
    }
}