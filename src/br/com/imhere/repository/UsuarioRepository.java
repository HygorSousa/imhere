package br.com.imhere.repository;

import br.com.imhere.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UsuarioRepository extends Repository<Usuario> {

    public UsuarioRepository(EntityManager em) {
        super(em, Usuario.class);
    }

    protected Class<Usuario> getModelClass() {
        return Usuario.class;
    }

    public Usuario buscarUsuarioLogado(String login, String senha) {
        Query query = getEntityManager().createQuery(
                "select " +
                        " usu " +
                        "from Usuario usu " +
                        "where usu.login = ?1 " +
                        "and usu.senha = ?2 ");

        query.setParameter(1, login);
        query.setParameter(2, senha);

        return super.buscarResultadoUnicoTipado(query, Usuario.class);
    }


}
