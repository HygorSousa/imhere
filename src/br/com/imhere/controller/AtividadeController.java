package br.com.imhere.controller;

import br.com.imhere.factory.JPAFactory;
import br.com.imhere.listController.AtividadeListController;
import br.com.imhere.model.Aluno;
import br.com.imhere.model.Atividade;
import br.com.imhere.repository.AlunoRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class AtividadeController extends Controller<Atividade> {

    public void abrirListAtividade() {
        AtividadeListController list = new AtividadeListController();
        list.openList(this::setEntity);
    }

    public List<Aluno> completeAluno(String query) {
        AlunoRepository repository = new AlunoRepository(JPAFactory.getEntityManager());
        return repository.buscarComplete(query, 5);
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
