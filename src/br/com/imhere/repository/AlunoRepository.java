package br.com.imhere.repository;

import br.com.imhere.model.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AlunoRepository extends DefaultRepository<Aluno> {

    public AlunoRepository(EntityManager em) {
        super(em);
    }

    protected Class<Aluno> getModelClass() {
        return Aluno.class;
    }

    public List<Object> buscarLazy(String search, Integer first, Integer pageSize, Integer lim) {
        Query query = getEntityManager().createNativeQuery(
                "select " +
                        "   alu.id, alu.nome, alu.matricula " +
                        "from  aluno alu " +
                        "where alu.nome ilike ?1 or alu.matricula ilike ?1 " +
                        "ORDER BY alu.nome");
        query.setParameter(1, "%" + search + "%");

        query.setFirstResult(first);
        query.setMaxResults(pageSize);

        return buscarSQL(query);
    }

    public Long buscarTodosRegistros(String search) {
        Query query = getEntityManager().createNativeQuery(
                "select " +
                        "   count(1) as count " +
                        "from  aluno alu " +
                        "where alu.nome ilike ?1 ");
        query.setParameter(1, "%" + search + "%");

        return (Long) buscarResultadoUnico(query);
    }


    public List<Aluno> buscarComplete(String search, int limit) {
        Query query = getEntityManager().createNativeQuery(
                "select " +
                        "   alu.* " +
                        "from aluno alu " +
                        "where (" +
                        "   alu.nome ilike ?1 or " +
                        "   alu.matricula ilike ?1" +
                        ") order by alu.nome " +
                        "limit ?2", Aluno.class);
        query.setParameter(1, "%" + search + "%");
        query.setParameter(2, limit);

        return buscar(query);
    }
}
