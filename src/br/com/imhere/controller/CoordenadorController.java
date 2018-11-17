package br.com.imhere.controller;

import br.com.imhere.listController.CoordenadorListController;
import br.com.imhere.model.Coordenador;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CoordenadorController extends Controller<Coordenador> {


    public void abrirListCoordenador() {
        CoordenadorListController list = new CoordenadorListController();
        list.openList(this::setEntity);
    }

    @Override
    public void limpar() {
        setEntity(new Coordenador());
    }

    @Override
    public Coordenador getEntity() {
        if (entity == null)
            entity = new Coordenador();
        return entity;
    }

}
