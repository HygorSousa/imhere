package br.com.imhere.model;

public enum TipoSemestre {
    PRIMEIROSEMESTRE(1, "1º Semestre"),
    SEGUNDOSEMESTRE(2, "2º Semestre");

    private final Integer valor;
    private final String label;

    TipoSemestre(Integer valorOpcao, String label) {
        this.valor = valorOpcao;
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