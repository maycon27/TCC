package com.tcc.doman.repository;


import com.tcc.api.dto.UsuarioResumDTO;
import com.tcc.doman.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

    @Query("select  new com.tcc.api.dto.UsuarioResumDTO(u.id,u.login,u.nome) from Usuario u")
    List<UsuarioResumDTO> findAllResum();

}

