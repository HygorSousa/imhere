package br.com.imhere.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Semestre extends DefaultEntity<Semestre> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ano;

    private TipoSemestre semestre;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public TipoSemestre getSemestre() {
        return semestre;
    }

    public void setSemestre(TipoSemestre semestre) {
        this.semestre = semestre;
    }

    public TipoSemestre[] getTipoSemestreValues() {
        return TipoSemestre.values();
    }


}
