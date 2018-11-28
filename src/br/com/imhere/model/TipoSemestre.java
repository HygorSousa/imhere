package br.com.imhere.model;

public enum TipoSemestre {
    PRIMEIROSEMESTRE(0, "1º Semestre"),
    SEGUNDOSEMESTRE(1, "2º Semestre");

    private final Integer valor;
    private final String label;

    TipoSemestre(Integer valor, String label) {
        this.valor = valor;
        this.label = label;
    }

    public Integer getValor() {
        return valor;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return Integer.toString(this.valor);
    }
}