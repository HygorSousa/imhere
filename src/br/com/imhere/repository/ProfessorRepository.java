package br.com.imhere.repository;

import br.com.imhere.model.Professor;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProfessorRepository extends DefaultRepository<Professor> {

    public ProfessorRepository(EntityManager em) {
        super(em);
    }

    protected Class<Professor> getModelClass() {
        return Professor.class;
    }

    @Override
    public List<Object> buscarLazy(String search, Integer first, Integer pageSize, Integer lim) {
        Query query = getEntityManager().createNativeQuery(
                "select " +
                        "   pro.id, pro.nome, pro.matricula " +
                        "from  professor pro " +
                        "where pro.nome ilike ?1 " +
                        "ORDER BY pro.nome");
        query.setParameter(1, "%" + search + "%");

        query.setFirstResult(first);
        query.setMaxResults(pageSize);

        return buscarSQL(query);
    }

    @Override
    public Long buscarTodosRegistros(String search) {
        Query query = getEntityManager().createNativeQuery(
                "select " +
                        "   count(1) as count " +
                        "from  professor pro " +
                        "where pro.nome ilike ?1 ");
        query.setParameter(1, "%" + search + "%");

        return (Long) buscarResultadoUnico(query);
    }


}
