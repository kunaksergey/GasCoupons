package ua.station.model.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sa on 04.11.17.
 */
@Entity
@Table(name="users")
public class User implements Serializable {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="email")
    private String email;

    @JsonIgnore
    @Column(name="password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user",fetch =FetchType.LAZY)
    private Basket basket;

    @Transient
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch =FetchType.LAZY)
    private List<Order> orderList;

    @Column(name="status")
    private boolean status=true;

    //список ролей
    @ManyToMany
    @JoinTable (name = "user_role_detail",
            joinColumns = @JoinColumn (name = "USER_ID"),
            inverseJoinColumns =@JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles=new HashSet<>();

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
     }

    public User(String email, String password, Basket basket) {
        this.email = email;
        this.password = password;
        this.basket=basket;
    }


    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
