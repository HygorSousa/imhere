package br.com.imhere.controller;


import br.com.imhere.listController.CursoListController;
import br.com.imhere.model.Curso;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CursoController extends Controller<Curso> {

    public void abrirListCurso() {
        CursoListController list = new CursoListController();
        list.openList(this::setEntity);
    }

    @Override
    public void limpar() {
        setEntity(new Curso());
    }

    @Override
    public Curso getEntity() {
        if (entity == null)
            entity = new Curso();
        return entity;
    }


}
