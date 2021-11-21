package ru.shop.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Date createTime;
    private User user;
    private String note;
    private String status;
    @ManyToMany
    private List<Product> products;
}
