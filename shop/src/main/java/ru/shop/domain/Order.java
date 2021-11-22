package ru.shop.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "note")
    private String note;

    @ManyToOne(targetEntity = StatusOrder.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "status_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_status_id"))
    private StatusOrder status;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_user_id"))
    private User user;

    @ManyToMany(cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "product_orders",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<Product> products;

    public Order() {
    }

    public Order(Long id, String note, StatusOrder status, User user, List<Product> products) {
        this.id = id;
        this.note = note;
        this.status = status;
        this.user = user;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id)  && Objects.equals(note, order.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", status='" + status.getStatus() + '\'' +
                '}';
    }
}
