package br.com.imhere.listController;

import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.Atividade;
import br.com.imhere.repository.AtividadeRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AtividadeListController extends DefaultListController<Atividade> {

    public AtividadeListController() {
        super(new AtividadeRepository(JPAFactory.getEntityManager()),
                true, false, false, 450, "95%", "/listings/listagemAtividade");
    }

}
