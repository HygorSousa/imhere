package br.com.imhere.model;

import javax.persistence.*;

@Entity
public class Usuario extends DefaultEntity<Usuario> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;

    private String senha;

    private TipoUsuario tipoUsuario;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Aluno aluno;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Coordenador coordenador;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Professor professor;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
