package br.com.imhere.filter;

import br.com.imhere.model.Usuario;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "securityFilter", urlPatterns = "/pages/*")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;

        HttpSession session = servletRequest.getSession(false);
        Usuario usuario = null;

        if (session != null)
            usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            ((HttpServletResponse) response).sendRedirect("/topicos2/login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}