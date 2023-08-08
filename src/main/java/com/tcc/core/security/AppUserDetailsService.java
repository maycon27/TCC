package com.tcc.core.security;


import com.tcc.doman.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    private List<GrantedAuthority> grantedAuths;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        var userOptional = repository.findByLogin(email);
        var usr = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));

        grantedAuths = new ArrayList<>();

        return new UsuarioSistema(usr, grantedAuths);

    }

}
