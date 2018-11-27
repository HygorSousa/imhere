package br.com.imhere.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAFactory {
    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("I'm Here");

    private JPAFactory() {
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
