package br.com.imhere.listController;


import br.com.imhere.application.SelectionListener;
import br.com.imhere.model.DefaultEntity;
import br.com.imhere.repository.Repository;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public abstract class ListController<T extends DefaultEntity<? super T>> implements Serializable {

    private static final long serialVersionUID = 4636455606742837323L;

    protected EntityManager em;
    protected T entity;
    protected List<Object> list;
    protected String listName;
    protected SelectionListener<T> listener;
    protected Repository<T> repository;
    private Boolean modal;
    private Boolean draggable;
    private Boolean resizable;
    private Object contentHeight;
    private Object contentWidth;
    protected String chave = "";

    public ListController(Repository<T> repository, Boolean modal, Boolean draggable, Boolean resizable, Object contentHeight, Object contentWidth, String xhtmlPageName) {
        this.modal = modal;
        this.draggable = draggable;
        this.resizable = resizable;
        this.contentHeight = contentHeight;
        this.contentWidth = contentWidth;
        this.listName = xhtmlPageName;
        this.repository = repository;
        String beanName = this.getClass().getSimpleName();
        getSessionScope().put(beanName.substring(0, 1).toLowerCase() + beanName.substring(1), this);
    }

    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null)
            fields = getAllFields(fields, type.getSuperclass());
        return fields;
    }

    public abstract T getEntity();

    public void setEntity(T entity) {
        this.entity = entity;
    }

    private void openList() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", this.modal);
        options.put("draggable", this.draggable);
        options.put("resizable", this.resizable);
        options.put("contentHeight", this.contentHeight);
        options.put("contentWidth", "100%");
        options.put("closable", false);
        options.put("closeOnEscape", true);
        options.put("sensivel", true);
        options.put("width", String.valueOf(this.contentWidth));
        options.put("height", this.contentHeight);
        options.put("widgetVar", "modalWidget");

        Map<String, List<String>> params = new HashMap<>();
        List<String> values = new ArrayList<>();
        setChave(String.valueOf(this.hashCode()));
        values.add(getChave());
        params.put("chave", values);

        PrimeFaces.current().dialog().openDynamic(listName, options, params);
        getSessionScope().put(getChave(), this);
    }

    public void openList(SelectionListener<T> listener) {
        openList();
        this.listener = listener;
        getSessionScope().put("listener", listener);
    }

    protected Map<String, Object> getSessionScope() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.getSessionMap();
    }

    protected void onSelect(T entity) {
        this.closeDialog(entity);
        if (this.listener == null) {
                this.listener = (SelectionListener<T>) getSessionScope().get("listener");
        }
        this.listener.select(entity);
    }

    public void onLoad() throws IOException {
        Object obj = getSessionScope().get(getChave());

        if (obj == null) {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(403, "Você não está autorizado a acessar esta página.");
            return;
        }

        List<Field> fields = new ArrayList<>();
        fields = getAllFields(fields, this.getClass());

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!Modifier.isFinal(field.getModifiers()))
                    field.set(this, field.get(obj));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeDialog() {
        this.limparChave();
        closeDialog(this.entity);
    }

    public void closeDialog(T entity) {
        this.limparChave();
        PrimeFaces.current().dialog().closeDynamic(entity);
    }

    public void limparChave() {
        if (getChave() == null || !getSessionScope().containsKey(getChave()))
            return;
        getSessionScope().remove(getChave());
    }

    public void onRowSelect(SelectEvent event) {
        onSelectById((Integer) event.getObject());
    }

    public void onSelectById(Integer id) {
        T entity;
        entity = repository.buscarId(id);
        setEntity(entity);
        onSelect(entity);
    }

    public Repository<T> getRepository() {
        return repository;
    }

    public void setRepository(Repository<T> repository) {
        this.repository = repository;
    }

    public List<T> lista() {
        return repository.lista();
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }
}
