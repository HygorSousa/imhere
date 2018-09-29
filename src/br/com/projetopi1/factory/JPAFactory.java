package br.com.projetopi1.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAFactory {
    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("Heimdall");

    private JPAFactory() {
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
