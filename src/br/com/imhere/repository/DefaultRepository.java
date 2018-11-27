package br.com.imhere.repository;


import br.com.imhere.model.DefaultEntity;
import br.com.imhere.util.ResultMapList;
import org.eclipse.persistence.exceptions.DatabaseException;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;

public abstract class DefaultRepository<T extends DefaultEntity<? super T>> {

    private Repository<T> repository;

    public DefaultRepository(EntityManager em) {
        repository = new Repository<>(em, getModelClass());
    }

    public DefaultRepository() {
        repository = new Repository<>(getEntityManager(), getModelClass());
    }

    public EntityManager getEntityManager() {
        return repository.getEntityManager();
    }

    protected abstract Class<T> getModelClass();

    public abstract List<Object> buscarLazy(String search, Integer first, Integer pageSize, Integer lim);

    public abstract Long buscarTodosRegistros(String search);

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

    public T buscarId(Integer id) {
        return repository.find(id);
    }

    public List<T> buscar(Query query) {
        return repository.find(query);
    }

    public List<Object> buscarSQL(Query query) {
        List<Object> lista = repository.findSQL(query);
        String colunas = query.toString().substring(query.toString().toLowerCase().lastIndexOf("select") + 6, query.toString().toLowerCase().indexOf("from"));
        if (!lista.isEmpty())
            return new ResultMapList(colunas, lista);
        return Collections.emptyList();
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
