package com.tcc.core.security;



import com.tcc.doman.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class UsuarioSistema extends User {


    private final Usuario usuario;


    public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getLogin(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }


    public Usuario getUsuario() {
        return usuario;
    }
}
