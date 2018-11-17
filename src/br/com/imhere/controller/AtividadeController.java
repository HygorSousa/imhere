package br.com.imhere.controller;


import br.com.imhere.listController.AtividadeListController;
import br.com.imhere.model.Atividade;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AtividadeController extends Controller<Atividade> {

    public void abrirListAtividade() {
        AtividadeListController list = new AtividadeListController();
        list.openList(this::setEntity);
    }

    @Override
    public void limpar() {
        setEntity(new Atividade());
    }

    @Override
    public Atividade getEntity() {
        if (entity == null)
            entity = new Atividade();
        return entity;
    }

}
