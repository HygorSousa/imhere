package br.com.imhere.controller;


import br.com.imhere.application.Util;
import br.com.imhere.factory.ProfessorFactory;
import br.com.imhere.listController.ProfessorListController;
import br.com.imhere.model.Curso;
import br.com.imhere.model.Professor;
import br.com.imhere.model.TipoUsuario;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ProfessorController extends Controller<Professor> {

    private List<Curso> listaCursos;

    @PostConstruct
    public void init() {
        getEntity().getUsuario().setProfessor(getEntity());
    }

    public void abrirListProfessor() {
        ProfessorListController list = new ProfessorListController();
        list.openList(this::setEntity);
    }

    @Override
    public void limpar() {
        setEntity(new Professor());
    }

    @Override
    public Professor incluir() {
        getEntity().getUsuario().setLogin(getEntity().getEmail());
        getEntity().getUsuario().setSenha(Util.encryptPassword(getEntity().getMatricula()));
        getEntity().getUsuario().setTipoUsuario(TipoUsuario.PROFESSOR);
        return super.incluir();
    }

    @Override
    public Professor getEntity() {
        if (entity == null)
            entity = ProfessorFactory.initialize();
        return entity;
    }

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }
}
