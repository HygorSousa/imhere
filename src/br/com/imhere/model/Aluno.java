package br.com.imhere.model;

import javax.persistence.*;

@Entity
public class Aluno extends DefaultEntity<Aluno> {

    @Id
    private Integer id;

    private String matricula;

    private String cpf;

    private String nome;

    private String email;

    private String biometria;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @MapsId("id")
    private Usuario usuario;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBiometria() {
        return biometria;
    }

    public void setBiometria(String biometria) {
        this.biometria = biometria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        if (this.matricula != null && this.nome != null)
            return this.matricula.concat(" - ").concat(this.nome);
        return "";
    }
}
