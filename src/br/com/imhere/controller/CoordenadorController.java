package br.com.imhere.controller;

import br.com.imhere.application.Util;
import br.com.imhere.factory.CoordenadorFactory;
import br.com.imhere.listController.CoordenadorListController;
import br.com.imhere.model.Coordenador;
import br.com.imhere.model.TipoUsuario;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CoordenadorController extends Controller<Coordenador> {

    @PostConstruct
    public void init() {
        getEntity().getUsuario().setCoordenador(getEntity());
    }

    public void abrirListCoordenador() {
        CoordenadorListController list = new CoordenadorListController();
        list.openList(this::setEntity);
    }

    @Override
    public void limpar() {
        setEntity(new Coordenador());
    }


    @Override
    public Coordenador incluir() {
        getEntity().getUsuario().setLogin(getEntity().getEmail());
        getEntity().getUsuario().setSenha(Util.encryptPassword(getEntity().getMatricula()));
        getEntity().getUsuario().setTipoUsuario(TipoUsuario.COORDENADOR);
        return super.incluir();
    }

    @Override
    public Coordenador getEntity() {
        if (entity == null)
            entity = CoordenadorFactory.initialize();
        return entity;
    }

}
