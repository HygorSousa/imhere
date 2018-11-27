package br.com.imhere.controller;


import br.com.imhere.application.Util;
import br.com.imhere.factory.AlunoFactory;
import br.com.imhere.listController.AlunoListController;
import br.com.imhere.model.Aluno;
import br.com.imhere.model.TipoUsuario;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AlunoController extends Controller<Aluno> {

    @PostConstruct
    public void init() {
        getEntity().getUsuario().setAluno(getEntity());
    }

    public void abrirListAluno() {
        AlunoListController list = new AlunoListController();
        list.openList(this::setEntity);
    }

    @Override
    public Aluno incluir() {
        getEntity().getUsuario().setLogin(getEntity().getEmail());
        getEntity().getUsuario().setSenha(Util.encryptPassword(getEntity().getMatricula()));
        getEntity().getUsuario().setTipoUsuario(TipoUsuario.ALUNO);
        return super.incluir();
    }

    @Override
    public void limpar() {
        setEntity(new Aluno());
    }

    @Override
    public Aluno getEntity() {
        if (entity == null)
            entity = AlunoFactory.initialize();
        return entity;
    }
}
