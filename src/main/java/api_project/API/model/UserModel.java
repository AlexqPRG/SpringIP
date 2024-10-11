package api_project.API.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @OneToOne(optional = true, mappedBy = "user")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private ClientModel client;

    //связь с таблицей сотрудники
    @OneToOne(optional = true, mappedBy = "user")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private StaffModel staff;

    public UserModel(){}

    public UserModel(UUID id, String username, String password, boolean isActive, Set<RoleEnum> roles, ClientModel client, StaffModel staff) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.roles = roles;
        this.client = client;
        this.staff = staff;
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

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    public StaffModel getStaff() {
        return staff;
    }

    public void setStaff(StaffModel staff) {
        this.staff = staff;
    }
}
