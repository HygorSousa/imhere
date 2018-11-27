package br.com.imhere.repository;


import br.com.imhere.model.DefaultEntity;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class Repository<T extends DefaultEntity<? super T>> {

    private final EntityManager entityManager;
    private final Class<T> c;

    public Repository(EntityManager em, Class<T> classe) {
        this.entityManager = em;
        this.c = classe;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public T find(Integer id) {
        return entityManager.getReference(c, id);
    }


    @SuppressWarnings("unchecked")
    public List<T> find(String jpql) {
        return entityManager.createQuery(jpql).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object> findSQL(Query query) {
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> find(Query query) {
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public Object findSingleResult(Query query) {

        return query.getSingleResult();
    }

    public T findSingleResultW(Query query, Class<T> tClass) {
        T object = (T) query.getSingleResult();
        return object;
    }

    @SuppressWarnings("unchecked")
    public List<T> list() {
        return entityManager.createQuery("select e from " + c.getName() + " e order by e.id").getResultList();
    }

    public T save(T t) throws javax.persistence.OptimisticLockException {
        try {
            return entityManager.merge(t);
        } catch (PersistenceException e) {
            if (e.getCause() instanceof OptimisticLockException)
                throw new javax.persistence.OptimisticLockException();
        }
        return t;
    }

    public void remove(T t) {
        entityManager.remove(t);
    }
}
