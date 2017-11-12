package ua.station.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sa on 05.11.17.
 */
@Entity
@Table(name="basket_item")
public class BasketItem implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    //@ManyToOne(optional = false, fetch = FetchType.EAGER)
    @ManyToOne
    @JsonIgnore
    private Basket basket;

    @JoinColumn(name = "price_id", referencedColumnName = "id")
    //@ManyToOne(optional = false, fetch = FetchType.EAGER)
    @ManyToOne
    private Price price;

    private Integer count;

    public BasketItem() {
    }

    public BasketItem(Price product, int count) {
        this.price=product;
        this.count=count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
