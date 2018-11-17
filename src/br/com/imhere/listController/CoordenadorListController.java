package br.com.imhere.listController;

import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.Coordenador;
import br.com.imhere.repository.CoordenadorRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class CoordenadorListController extends DefaultListController<Coordenador> {

    public CoordenadorListController() {
        super(new CoordenadorRepository(JPAFactory.getEntityManager()),
                true, false, false, 450, "95%", "/listings/listagemCoordenador");
    }

}
