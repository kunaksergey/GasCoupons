package ua.station.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sa on 04.11.17.
 */
@Entity
@Table(name = "order")
public class Order implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonIgnore
    @Column(name="user")
    User user;
    @Column(name="status")
    Integer status;
}
