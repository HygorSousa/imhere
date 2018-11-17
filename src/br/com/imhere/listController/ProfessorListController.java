package br.com.imhere.listController;

import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.Professor;
import br.com.imhere.repository.ProfessorRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ProfessorListController extends DefaultListController<Professor> {

    public ProfessorListController() {
        super(new ProfessorRepository(JPAFactory.getEntityManager()),
                true, false, false, 450, "95%", "/listings/listagemProfessor");
    }

}
