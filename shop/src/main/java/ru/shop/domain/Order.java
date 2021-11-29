package ru.shop.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_user_id"))
    private User user;
    @Column(name = "note")
    private String note;
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_status_id"))
    private StatusOrder status;

    @OneToMany( mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<OrderItem> productOrders = new ArrayList<>();

    public Order() {
    }

    public Order(Long id, User user, String note, StatusOrder status) {
        this.id = id;
        this.user = user;
        this.note = note;
        this.status = status;
    }

    public Order(Long id, User user, String note, StatusOrder status, List<OrderItem> productOrders) {
        this.id = id;
        this.user = user;
        this.note = note;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<OrderItem> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<OrderItem> productOrders) {
        this.productOrders = productOrders;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(note, order.note);
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
                ", status=" + status +
                '}';
    }
}
