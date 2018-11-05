package br.com.imhere.repository;

import br.com.imhere.model.DefaultEntity;
import javafx.collections.transformation.TransformationList;
import org.eclipse.persistence.exceptions.OptimisticLockException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Repository<T extends DefaultEntity<? super T>> {

    private final EntityManager entityManager;
    private final Class<T> clazz;

    public Repository(EntityManager em, Class<T> classe) {
        this.entityManager = em;
        this.clazz = classe;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public T buscarId(Integer id) {
        return entityManager.getReference(clazz, id);
    }


    @SuppressWarnings("unchecked")
    public List<T> buscar(String jpql) {
        return entityManager.createQuery(jpql).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object> buscarSQL(Query query) {
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> buscar(Query query) {
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public Object buscarResultadoUnico(Query query) {
        return query.getSingleResult();
    }

    public T buscarResultadoUnicoTipado(Query query, Class<T> clazz) {
        T object = (T) query.getSingleResult();
        return object;
    }

    @SuppressWarnings("unchecked")
    public List<T> lista() {
        return entityManager.createQuery("select e from " + clazz.getName() + " e order by e.id").getResultList();
    }

    public T salvar(T t) throws javax.persistence.OptimisticLockException {
        try {
            return entityManager.merge(t);
        } catch (PersistenceException e) {
            if (e.getCause() instanceof OptimisticLockException)
                throw new javax.persistence.OptimisticLockException();
        }
        return t;
    }

    public void remover(T t) {
            entityManager.remove(t);
    }

    public Class<T> getClassModel() {
        return clazz;
    }

    public List<Object> buscarLazy(String search, Integer first, Integer pageSize, Integer lim){
        return new ArrayList<>();
    }

    public Long buscarTodosRegistros(String search){
        return 0L;
    }
}
