package br.com.heimdall.repository;

import br.com.heimdall.model.DefaultEntity;

import javax.persistence.EntityManager;

public class Repository<T extends DefaultEntity<T>> {
    private EntityManager em;

    public Repository(EntityManager em) {
        this.em = em;
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public T save(T entity) {
        return getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(entity);
    }
}
