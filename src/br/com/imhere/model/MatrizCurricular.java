package br.com.imhere.model;

import javax.persistence.*;

@Entity
public class MatrizCurricular extends DefaultEntity<MatrizCurricular> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer qntHoras;

    @ManyToOne
    @JoinColumn(name = "idaluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "idcurso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "iddisciplina")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "idprofessor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "idcoordenador")
    private Coordenador coordenador;

    @ManyToOne
    @JoinColumn(name = "idsemestre")
    private Semestre semestre;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQntHoras() {
        return qntHoras;
    }

    public void setQntHoras(Integer qntHoras) {
        this.qntHoras = qntHoras;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }
}
