package ua.station.model.entity;

import ua.station.model.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sa on 02.09.17.
 */
@Entity
@Table(name="role")
public class Role {

    private String role;

    @ElementCollection(targetClass=User.class)
    private Set<User> userSet;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    @Id
    @Column(name="ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToMany
    @JoinTable (name = "user_role_detail",
            joinColumns = @JoinColumn (name = "ROLE_ID"),
            inverseJoinColumns =@JoinColumn(name = "USER_ID"))
    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        return role != null ? role.equals(role1.role) : role1.role == null;

    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }
}