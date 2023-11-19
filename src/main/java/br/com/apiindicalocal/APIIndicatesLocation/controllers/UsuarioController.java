package br.com.apiindicalocal.APIIndicatesLocation.controllers;

import br.com.apiindicalocal.APIIndicatesLocation.domain.user.Usuarios;
import br.com.apiindicalocal.APIIndicatesLocation.dtos.LoginDto;
import br.com.apiindicalocal.APIIndicatesLocation.dtos.LoginResponseDto;
import br.com.apiindicalocal.APIIndicatesLocation.dtos.RegisterDTO;
import br.com.apiindicalocal.APIIndicatesLocation.repositories.UsuarioRepositorio;
import br.com.apiindicalocal.APIIndicatesLocation.infra.security.services.TokenService;
import br.com.apiindicalocal.APIIndicatesLocation.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    private ResponseEntity listar(@RequestBody @Valid LoginDto dados) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuarios) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuarios newUser = new Usuarios(data.username(), encryptPassword, data.roles());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(newUser));
    }


}
