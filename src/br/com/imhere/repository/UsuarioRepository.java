package br.com.imhere.repository;

import br.com.imhere.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UsuarioRepository extends DefaultRepository<Usuario> {

    public UsuarioRepository(EntityManager em) {
        super(em);
    }

    protected Class<Usuario> getModelClass() {
        return Usuario.class;
    }

    @Override
    public List<Object> buscarLazy(String search, Integer first, Integer pageSize, Integer lim) {
        return null;
    }

    @Override
    public Long buscarTodosRegistros(String search) {
        return null;
    }

    public Usuario buscarUsuarioLogado(String login, String senha) {
        Query query = getEntityManager().createQuery(
                "select " +
                        " usu " +
                        "from Usuario usu " +
                        "where usu.login = ?1 " +
                        "and usu.senha = ?2 ", Usuario.class);

        query.setParameter(1, login);
        query.setParameter(2, senha);

        return super.buscarResultadoUnicoTipado(query);
    }


}
