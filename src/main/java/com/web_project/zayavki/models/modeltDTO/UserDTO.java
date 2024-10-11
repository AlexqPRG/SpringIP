package com.web_project.zayavki.models.modeltDTO;

import com.web_project.zayavki.models.ClientModel;
import com.web_project.zayavki.models.RoleEnum;

import java.util.Set;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String username;
    private String password;
    private Set<RoleEnum> roles;
    private boolean isActive;


    public UserDTO(){}

    public UserDTO(ClientModel clientModel){
        this.id = clientModel.getUser().getId();
        this.username = clientModel.getUser().getUsername();
        this.password = clientModel.getUser().getPassword();
        this.roles = clientModel.getUser().getRoles();
        this.isActive = clientModel.getUser().isActive();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
