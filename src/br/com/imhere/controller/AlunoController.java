package br.com.imhere.controller;


import br.com.imhere.listController.AlunoListController;
import br.com.imhere.model.Aluno;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AlunoController extends Controller<Aluno> {

    public void abrirListAluno() {
        AlunoListController list = new AlunoListController();
        list.openList(this::setEntity);
    }

    @Override
    public Aluno incluir() {

        return super.incluir();
    }

    @Override
    public void limpar() {
        setEntity(new Aluno());
    }

    @Override
    public Aluno getEntity() {
        if (entity == null)
            entity = new Aluno();
        return entity;
    }
}
