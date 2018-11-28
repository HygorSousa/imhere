package br.com.imhere.converter;


import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.Professor;
import br.com.imhere.repository.ProfessorRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "professorConverter")
public class ProfessorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        ProfessorRepository repository = new ProfessorRepository(JPAFactory.getEntityManager());
        return repository.buscarId(Integer.parseInt(arg2));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2 != null)
            return ((Professor) arg2).getId() == null ? "" : ((Professor) arg2).getId().toString();
        return "";
    }
}