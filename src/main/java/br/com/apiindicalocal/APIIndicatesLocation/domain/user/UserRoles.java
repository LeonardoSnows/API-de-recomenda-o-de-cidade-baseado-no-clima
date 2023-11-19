package br.com.apiindicalocal.APIIndicatesLocation.domain.user;

public enum UserRoles {

    ADMIN("admin"),
    USER("user");

    private String roles;

    UserRoles(String roles) {
        this.roles = roles;
    }

    public String getRole() {
        return roles;
    }


}
