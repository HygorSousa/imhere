package br.com.imhere.converter;


import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.Curso;
import br.com.imhere.repository.CursoRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "cursoConverter")
public class CursoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        CursoRepository repository = new CursoRepository(JPAFactory.getEntityManager());
        return repository.buscarId(Integer.parseInt(arg2));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2 != null)
            return ((Curso) arg2).getId() == null ? "" : ((Curso) arg2).getId().toString();
        return "";
    }
}