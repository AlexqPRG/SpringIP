package api_project.API.model.modeltDTO;


import api_project.API.model.ClientModel;
import api_project.API.model.RoleEnum;
import api_project.API.model.UserModel;

import java.util.Set;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String username;
    private String password;
    private Set<RoleEnum> roles;
    private boolean isActive;


    public UserDTO(){}

    public UserDTO(UserModel userModel) {
        this.id = userModel.getId();
        this.username = userModel.getUsername();
        this.password = userModel.getPassword();
        this.roles = userModel.getRoles();
        this.isActive = userModel.isActive();
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
