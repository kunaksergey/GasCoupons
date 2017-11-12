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
    private Integer count;

    public BasketItem() {
    }

    public BasketItem(Price product, int count) {
        this.price = product;
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


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
