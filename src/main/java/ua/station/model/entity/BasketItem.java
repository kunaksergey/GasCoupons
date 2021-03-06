package ua.station.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sa on 05.11.17.
 */
@Entity
@Table(name = "basket_item")
public class BasketItem implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private BasketItemPK basketItemPk = new BasketItemPK();


    @MapsId("basketId")
    @JoinColumn(name = "basket_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonIgnore
    private Basket basket;

    @MapsId("priceId")
    @JoinColumn(name = "price_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Price price;

    @Column(name="count")
    private int count;

    public BasketItem() {
    }

     public BasketItem(Basket basket, Price price, int count) {
        this.basket=basket;
        this.price = price;
        this.count = count;
    }


    public BasketItemPK getBasketItemPk() {
        return basketItemPk;
    }

    public void setBasketItemPk(BasketItemPK basketItemPk) {
        this.basketItemPk = basketItemPk;
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


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
