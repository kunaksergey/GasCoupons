package ua.station.model.entity;

import javax.persistence.*;

/**
 * Created by sa on 05.11.17.
 */
@Entity
@Table(name="basket_item")
public class BasketItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Basket basket;

    @JoinColumn(name = "price_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Price price;
    private Integer count;

    public BasketItem(Price product, int count) {
        this.price=product;
        this.count=count;
    }


}
