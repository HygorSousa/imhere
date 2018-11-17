package br.com.imhere.repository;

import br.com.imhere.model.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AlunoRepository extends Repository<Aluno> {

    public AlunoRepository(EntityManager em) {
        super(em, Aluno.class);
    }

    protected Class<Aluno> getModelClass() {
        return Aluno.class;
    }

    @Override
    public List<Object> buscarLazy(String search, Integer first, Integer pageSize, Integer lim) {
        Query query = getEntityManager().createNativeQuery(
                "select " +
                        "   alu.id, alu.nome " +
                        "from  aluno alu " +
                        "where alu.nome ilike ?1 " +
                        "ORDER BY alu.nome");
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
                        "from  aluno alu " +
                        "where alu.nome ilike ?1 ");
        query.setParameter(1, "%" + search + "%");

        return (Long) buscarResultadoUnico(query);
    }


}
