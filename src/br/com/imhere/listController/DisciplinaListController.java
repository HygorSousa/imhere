package br.com.imhere.listController;

import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.Disciplina;
import br.com.imhere.repository.DisciplinaRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class DisciplinaListController extends DefaultListController<Disciplina> {

    public DisciplinaListController() {
        super(new DisciplinaRepository(JPAFactory.getEntityManager()),
                true, false, false, 450, "95%", "/listings/listagemDisciplina");
    }

}
