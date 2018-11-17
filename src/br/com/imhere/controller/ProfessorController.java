package br.com.imhere.controller;


import br.com.imhere.listController.ProfessorListController;
import br.com.imhere.model.Curso;
import br.com.imhere.model.Professor;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ProfessorController extends Controller<Professor> {

    private List<Curso> listaCursos;

    public void abrirListProfessor() {
        ProfessorListController list = new ProfessorListController();
        list.openList(this::setEntity);
    }

    @Override
    public void limpar() {
        setEntity(new Professor());
    }

    @Override
    public Professor getEntity() {
        if (entity == null)
            entity = new Professor();
        return entity;
    }

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }
}
