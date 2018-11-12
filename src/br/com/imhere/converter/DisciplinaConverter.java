package br.com.imhere.converter;


import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.Disciplina;
import br.com.imhere.repository.DisciplinaRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Disciplina.class)
public class DisciplinaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        DisciplinaRepository repository = new DisciplinaRepository(JPAFactory.getEntityManager());
        return repository.buscarId(Integer.parseInt(arg2));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2 != null)
            return ((Disciplina) arg2).getId() == null ? "" : ((Disciplina) arg2).getId().toString();
        return "";
    }
}