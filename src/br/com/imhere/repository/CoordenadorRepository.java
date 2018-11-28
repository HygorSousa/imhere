package br.com.imhere.repository;


import br.com.imhere.model.Coordenador;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CoordenadorRepository extends DefaultRepository<Coordenador> {

    public CoordenadorRepository(EntityManager em) {
        super(em);
    }

    public Class<Coordenador> getModelClass() {
        return Coordenador.class;
    }

    @Override
    public List<Object> buscarLazy(String search, Integer first, Integer pageSize, Integer lim) {
        Query query = getEntityManager().createNativeQuery(
                "select " +
                        "   coo.id, coo.nome " +
                        "from  coordenador coo " +
                        "where coo.nome ilike ?1 " +
                        "ORDER BY coo.nome");
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
                        "from  coordenador coo " +
                        "where coo.nome ilike ?1 ");
        query.setParameter(1, "%" + search + "%");

        return (Long) buscarResultadoUnico(query);
    }

    public List<Coordenador> buscarComplete(String search, int limit) {
        Query query = getEntityManager().createNativeQuery(
                "select " +
                        "   coo.* " +
                        "from coordenador coo " +
                        "where (" +
                        "   coo.nome ilike ?1 or " +
                        "   coo.matricula ilike ?1" +
                        ") order by coo.nome " +
                        "limit ?2", Coordenador.class);
        query.setParameter(1, "%" + search + "%");
        query.setParameter(2, limit);

        return buscar(query);
    }

}
