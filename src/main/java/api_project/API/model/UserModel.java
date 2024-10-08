package api_project.API.model;


import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
public class UserModel {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String password;
    private boolean isActive;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;

    //связь с таблицей клиенты
    @OneToOne(optional = false, mappedBy = "user")
    private ClientModel owner_user;

    //связь с таблицей сотрудники
    @OneToOne(optional = false, mappedBy = "user")
    private StaffModel owner_staff;

    public UserModel(){}

    public UserModel(UUID id, String username, String password, boolean isActive, Set<RoleEnum> roles, ClientModel owner_user, StaffModel owner_staff) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.roles = roles;
        this.owner_user = owner_user;
        this.owner_staff = owner_staff;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }

    public ClientModel getOwner_user() {
        return owner_user;
    }

    public void setOwner_user(ClientModel owner_user) {
        this.owner_user = owner_user;
    }

    public StaffModel getOwner_staff() {
        return owner_staff;
    }

    public void setOwner_staff(StaffModel owner_staff) {
        this.owner_staff = owner_staff;
    }
}
