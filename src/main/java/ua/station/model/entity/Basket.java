package ua.station.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Корзина
 */
@Entity
@Table(name = "basket")
public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade =  CascadeType.ALL, mappedBy = "basket",orphanRemoval = true)
    private List<BasketItem> basketItems;

    public Basket() {

    }

    public Basket(User user) {
        this.user=user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Basket basket = (Basket) o;

        if (id != null ? !id.equals(basket.id) : basket.id != null) return false;
        return user != null ? user.equals(basket.user) : basket.user == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
