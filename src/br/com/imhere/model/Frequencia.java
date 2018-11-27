package br.com.imhere.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Frequencia extends DefaultEntity<Frequencia> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(value = TemporalType.DATE)
    private Date horaInicio;

    @Temporal(value = TemporalType.DATE)
    private Date horaFim;

    @ManyToOne
    @JoinColumn(name = "idaluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "idmatrizcurricular")
    private MatrizCurricular matrizCurricular;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public MatrizCurricular getMatrizCurricular() {
        return matrizCurricular;
    }

    public void setMatrizCurricular(MatrizCurricular matrizCurricular) {
        this.matrizCurricular = matrizCurricular;
    }
}
