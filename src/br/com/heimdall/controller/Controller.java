package br.com.heimdall.controller;

import br.com.heimdall.application.Util;
import br.com.heimdall.factory.JPAFactory;
import br.com.heimdall.model.DefaultEntity;
import br.com.heimdall.repository.Repository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class Controller<T extends DefaultEntity<T>> implements Serializable {

    protected T entity = null;
    private EntityManager em = null;

    public abstract void limpar();

    public T incluir() {
        Repository<T> repository = new Repository<T>(getEntityManager());
        getEntityManager().getTransaction().begin();

        T result = repository.save(getEntity());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getEntityManager().getTransaction().commit();
        limpar();
        Util.addInfoMessage("Inclusão realizada com sucesso!");
        return result;
    }

    public T alterar() {
        Repository<T> repository = new Repository<T>(getEntityManager());
        getEntityManager().getTransaction().begin();

        T result = repository.save(getEntity());

        getEntityManager().getTransaction().commit();
        limpar();
        Util.addInfoMessage("Alteração realizada com sucesso!");
        return result;
    }

    public void remover() {
        Repository<T> repository = new Repository<T>(getEntityManager());
        getEntityManager().getTransaction().begin();

        repository.remove(getEntity());

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
