package br.com.imhere.controller;

import br.com.imhere.application.Util;
import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.DefaultEntity;
import br.com.imhere.repository.Repository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class Controller<T extends DefaultEntity<T>> implements Serializable {

    protected T entity = null;
    private EntityManager em = null;
    private Class<T> clazz;

    public abstract void limpar();

    public T incluir() {
        Repository<T> repository = new Repository<>(em, clazz);
        getEntityManager().getTransaction().begin();
        T result = repository.salvar(getEntity());
        getEntityManager().getTransaction().commit();
        limpar();
        Util.addInfoMessage("Inclusão realizada com sucesso!");
        return result;
    }

    public T alterar() {
        Repository<T> repository = new Repository<>(em, clazz);

        getEntityManager().getTransaction().begin();

        T result = repository.salvar(getEntity());

        getEntityManager().getTransaction().commit();
        limpar();
        Util.addInfoMessage("Alteração realizada com sucesso!");
        return result;
    }

    public void remover() {
        Repository<T> repository = new Repository<>(em, clazz);

        getEntityManager().getTransaction().begin();

        repository.remover(getEntity());

        getEntityManager().getTransaction().commit();
        limpar();
        Util.addInfoMessage("Remoção realizada com sucesso!");
    }

    public abstract T getEntity();

    public void setEntity(T entity) {
        this.entity = entity;
    }

    protected EntityManager getEntityManager() {
        if (em == null)
            em = JPAFactory.getEntityManager();
        return em;
    }

}
