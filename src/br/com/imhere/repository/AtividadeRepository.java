package br.com.imhere.repository;

import br.com.imhere.model.Atividade;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AtividadeRepository extends Repository<Atividade> {

    public AtividadeRepository(EntityManager em) {
        super(em, Atividade.class);
    }

    protected Class<Atividade> getModelClass() {
        return Atividade.class;
    }

    @Override
    public List<Object> buscarLazy(String search, Integer first, Integer pageSize, Integer lim) {
        Query query = getEntityManager().createNativeQuery(
                "select " +
                        "   ati.id, ati.nome " +
                        "from  atividade ati " +
                        "where ati.nome ilike ?1 " +
                        "ORDER BY ati.nome");
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
                        "from  atividade ati " +
                        "where ati.nome ilike ?1 ");
        query.setParameter(1, "%" + search + "%");

        return (Long) buscarResultadoUnico(query);
    }


}
