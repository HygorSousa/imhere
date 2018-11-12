package br.com.imhere.controller;



import br.com.imhere.factory.JPAFactory;
import br.com.imhere.listController.DisciplinaListController;
import br.com.imhere.model.Curso;
import br.com.imhere.model.Disciplina;
import br.com.imhere.repository.CursoRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class DisciplinaController extends Controller<Disciplina> {

    private List<Curso> listaCursos;

    @PostConstruct
    public void init() {
        CursoRepository repository = new CursoRepository(JPAFactory.getEntityManager());
        setListaCursos(repository.lista());
    }

    public void abrirListDisciplina() {
        DisciplinaListController list = new DisciplinaListController();
        list.openList(this::setEntity);
    }

    @Override
    public void limpar() {
        setEntity(new Disciplina());
    }

    @Override
    public Disciplina getEntity() {
        if (entity == null)
            entity = new Disciplina();
        return entity;
    }

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }
}
