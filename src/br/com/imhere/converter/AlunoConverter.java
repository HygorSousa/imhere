package br.com.imhere.converter;


import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.Aluno;
import br.com.imhere.repository.AlunoRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "alunoConverter")
public class AlunoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        AlunoRepository repository = new AlunoRepository(JPAFactory.getEntityManager());
        return repository.buscarId(Integer.parseInt(arg2));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2 != null)
            return ((Aluno) arg2).getId() == null ? "" : ((Aluno) arg2).getId().toString();
        return "";
    }
}