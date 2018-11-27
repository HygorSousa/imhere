package br.com.imhere.repository;

import br.com.imhere.model.MatrizCurricular;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MatrizCurricularRepository extends DefaultRepository<MatrizCurricular> {

    public MatrizCurricularRepository(EntityManager em) {
        super(em);
    }

    protected Class<MatrizCurricular> getModelClass() {
        return MatrizCurricular.class;
    }

    @Override
    public List<Object> buscarLazy(String search, Integer first, Integer pageSize, Integer lim) {
        Query query = getEntityManager().createNativeQuery(
                "select " +
                        "   cur.id, cur.nome " +
                        "from  matrizCurricular cur " +
                        "where cur.nome ilike ?1 " +
                        "ORDER BY cur.nome");
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
                        "from  matrizCurricular cur " +
                        "where cur.nome ilike ?1 ");
        query.setParameter(1, "%" + search + "%");

        return (Long) buscarResultadoUnico(query);
    }


}
