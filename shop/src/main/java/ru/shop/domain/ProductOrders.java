package ru.shop.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_orders")
public class ProductOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_product_id"))
    private Product product;

    @Column(name = "product_count", nullable = false)
    private Long productCount;

    public ProductOrders() {
    }

    public ProductOrders(Long id, Product product, Long productCount) {
        this.id = id;
        this.product = product;
        this.productCount = productCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOrders that = (ProductOrders) o;
        return Objects.equals(id, that.id) && Objects.equals(productCount, that.productCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productCount);
    }

    @Override
    public String toString() {
        return "ProductOrders{" +
                "id=" + id +
                ", product=" + product.getName() +
                ", productCount=" + productCount +
                '}';
    }
}
