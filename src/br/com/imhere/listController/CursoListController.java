package br.com.imhere.listController;

import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.Curso;
import br.com.imhere.repository.CursoRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CursoListController extends DefaultListController<Curso> {

    public CursoListController() {
        super(new CursoRepository(JPAFactory.getEntityManager()),
                true, false, false, 450, "95%", "/listings/listagemCurso");
    }

}
