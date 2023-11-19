package br.com.apiindicalocal.APIIndicatesLocation.dtos;

import br.com.apiindicalocal.APIIndicatesLocation.domain.user.UserRoles;

public record RegisterDTO(String username, String password, UserRoles roles) {
}
