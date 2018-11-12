package br.com.imhere.repository;


import br.com.imhere.model.Disciplina;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DisciplinaRepository extends Repository<Disciplina> {

    public DisciplinaRepository(EntityManager em) {
        super(em, Disciplina.class);
    }

    protected Class<Disciplina> getModelClass() {
        return Disciplina.class;
    }

    @Override
    public List<Object> buscarLazy(String search, Integer first, Integer pageSize, Integer lim) {
        Query query = getEntityManager().createNativeQuery(
                "select " +
                        "   dis.id, dis.nome as disciplina, cur.nome as curso " +
                        "from disciplina dis " +
                        "inner join curso cur on dis.idcurso = cur.id " +
                        "where (dis.nome ilike ?1 and cur.nome ilike ?1)" +
                        "ORDER by dis.nome");
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
                        "from disciplina dis " +
                        "inner join curso cur on dis.idcurso = cur.id " +
                        "where (dis.nome ilike ?1 and cur.nome ilike ?1)");
        query.setParameter(1, "%" + search + "%");

        return (Long) buscarResultadoUnico(query);
    }

    public List<Disciplina> buscarComplete(String search, Integer reg) {
        Query query = getEntityManager().createNativeQuery(
                "select dis.* " +
                        "from disciplina dis " +
                        "where dis.nome ILIKE ?1 " +
                        "order by dis.nome " +
                        "limit ?2", Disciplina.class);
        query.setParameter(1, "%" + search + "%");
        query.setParameter(2, reg);

        return super.buscar(query);
    }
}
