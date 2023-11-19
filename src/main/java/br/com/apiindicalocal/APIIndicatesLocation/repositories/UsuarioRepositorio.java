package br.com.apiindicalocal.APIIndicatesLocation.repositories;

import br.com.apiindicalocal.APIIndicatesLocation.domain.user.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositorio extends JpaRepository<Usuarios, String> {

    UserDetails findByUsername(String username);
}
