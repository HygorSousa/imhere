package br.com.imhere.listController;

import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.Aluno;
import br.com.imhere.repository.AlunoRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AlunoListController extends DefaultListController<Aluno> {

    public AlunoListController() {
        super(new AlunoRepository(JPAFactory.getEntityManager()),
                true, false, false, 450, "95%", "/listings/listagemAluno");
    }

}
