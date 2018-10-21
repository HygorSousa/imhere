package br.com.imhere.repository;

import br.com.imhere.model.DefaultEntity;
import org.eclipse.persistence.exceptions.DatabaseException;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class DefaultRepository<T extends DefaultEntity<? super T>> {

    private Repository<T> repository;

    public DefaultRepository(EntityManager em) {
        repository = new Repository<T>(em, getModelClass());
    }

    public DefaultRepository() {
        repository = new Repository<T>(getEntityManager(), getModelClass());
    }

    public EntityManager getEntityManager() {
        return repository.getEntityManager();
    }

    protected abstract Class<T> getModelClass();

    public Class<T> getClassModel() {
        return getModelClass();
    }

    public T salvar(T t) throws javax.persistence.OptimisticLockException {
        try {
            return repository.save(t);
        } catch (OptimisticLockException e) {
            throw new OptimisticLockException();
        } catch (IllegalStateException | DatabaseException | IllegalArgumentException | ConstraintViolationException e) {
            if (e instanceof ConstraintViolationException) {
                String fields = "";
                for (ConstraintViolation constraintViolation : ((ConstraintViolationException) e).getConstraintViolations())
                    fields = fields.concat(constraintViolation.toString() + " -");
            }
        }
        return t;
    }

    public T buscar(Integer id) {
        return repository.find(id);

    }

    public List<T> buscar(Query query) {
        return repository.find(query);

    }

    public List<Object> buscarSQL(Query query, String colunas) {
        return repository.findSQL(query);
    }


    public List<Object> buscarSQL(Query query) {
        return repository.findSQL(query);
    }

    public Object buscarResultadoUnico(Query query) {
        query.setMaxResults(1);
        return repository.findSingleResult(query);
    }

    @SuppressWarnings("unchecked")
    public T buscarResultadoUnicoTipado(Query query) {
        query.setMaxResults(1);
        return (T) repository.findSingleResult(query);
    }

    public List<T> lista() {
        return repository.list();
    }

    public void remover(T t) {
        repository.remove(t);
    }


    public void saveLista(EntityManager em, List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            repository.save(list.get(i));
            if (i % 300 == 0) {
                em.flush();
                em.clear();
            }
        }
    }

}
