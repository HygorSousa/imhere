package br.com.imhere.listController;

import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.MatrizCurricular;
import br.com.imhere.repository.MatrizCurricularRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class MatrizCurricularListController extends DefaultListController<MatrizCurricular> {

    public MatrizCurricularListController() {
        super(new MatrizCurricularRepository(JPAFactory.getEntityManager()),
                true, false, false, 450, "95%", "/listings/listagemMatrizCurricular");
    }

}
