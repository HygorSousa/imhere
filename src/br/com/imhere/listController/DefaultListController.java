package br.com.imhere.listController;

import br.com.imhere.model.DefaultEntity;
import br.com.imhere.repository.Repository;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

public class DefaultListController<T extends DefaultEntity<? super T>> extends ListController<T> {

    private static final long serialVersionUID = -2418075386938070418L;

    private String searchField;
    private Class<T> clazz;
    private LazyDataModel<Object> lazyModel;


    public DefaultListController(Repository<T> repository, Boolean modal, Boolean draggable,
                                 Boolean resizable, Object contentHeight, Object contentWidth, String xhtmlPageName) {
        super(repository, modal, draggable, resizable, contentHeight, contentWidth, xhtmlPageName);
        this.init();
        this.clazz = repository.getClassModel();
    }

    protected void init() {
        String beanName = this.getClass().getSimpleName();
        getSessionScope().put(beanName.substring(0, 1).toLowerCase() + beanName.substring(1), this);
        lazyModel = new LazyDataModel<>() {
            private static final long serialVersionUID = -6721098016982208909L;

            @Override
            public Object getRowData(String rowKey) {
                return new Integer(rowKey);
            }

            @SuppressWarnings("unchecked")
            @Override
            public Object getRowKey(Object obj) {
                if (obj instanceof Map)
                    return ((Map) obj).get("id");
                return obj;
            }

            @Override
            public List<Object> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                setRowCount(getAllRecords());
                return list = getLazyLista(first, pageSize);
            }
        };
    }

    @Override
    public T getEntity() {
        if (entity == null) {
            try {
                entity = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    public void search() {
        list = null;
        init();
    }

    public LazyDataModel<Object> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Object> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public void onRowSelect(SelectEvent event) {
        onSelectById((Integer) event.getObject());
    }

    public int getAllRecords() {
        return repository.buscarTodosRegistros(getSearchField()).intValue();
    }

    public List<Object> getLazyLista(Integer first, Integer pageSize) {
        return repository.buscarLazy(getSearchField(), first, pageSize, null);
    }

    public String getSearchField() {
        if (searchField == null)
            searchField = "";
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

}
