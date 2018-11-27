package br.com.imhere.controller;

import br.com.imhere.application.Util;
import br.com.imhere.factory.JPAFactory;
import br.com.imhere.model.Usuario;
import br.com.imhere.repository.UsuarioRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginController extends DefaultController {
    private static final long serialVersionUID = -2482810615603773001L;

    private Usuario usuario;

    public String entrar() {
        UsuarioRepository repository =
                new UsuarioRepository(JPAFactory.getEntityManager());
        Usuario usuarioValidado = repository
                .buscarUsuarioLogado(getUsuario().getLogin(), getUsuario().getSenha());

        if (usuarioValidado == null) {
            Util.addErroMessage("Usuário ou Senha inválido.");
            return null;
        }

        setUsuarioLogado(usuarioValidado);

        return "cadastropessoa4.xhtml";
    }

    public Usuario getUsuario() {
        if (usuario == null)
            usuario = new Usuario();
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}