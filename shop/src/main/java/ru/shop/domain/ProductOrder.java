package ru.shop.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_orders")
public class ProductOrder {
    @Id
    private Long id;
    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_order_id"))
    private Order order;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_product_id"))
    private Product product;

    @Column(name = "product_count")
    private Long count;

    public ProductOrder() {
    }

    public ProductOrder(Long id, Order order, Product product, Long count) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrder that = (ProductOrder) o;
        return Objects.equals(id, that.id) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count);
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product.getName() +
                ", count=" + count +
                '}';
    }
}
