package br.com.apiindicalocal.APIIndicatesLocation.services;

import br.com.apiindicalocal.APIIndicatesLocation.domain.user.Usuarios;
import br.com.apiindicalocal.APIIndicatesLocation.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio repository;

    public Usuarios save(Usuarios newUser) {
        return repository.save(newUser);
    }
}
