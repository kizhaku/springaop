package org.kizhaku.springaop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    @Setter(AccessLevel.NONE)
    private UUID product_id;
    private String product_name;
    private String product_rating;
    private String product_type;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date create_date;
    private Boolean is_active;

}
