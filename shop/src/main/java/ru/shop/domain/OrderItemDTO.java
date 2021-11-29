package ru.shop.domain;

import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
public class OrderItemDTO {
    private Long id;
    private OrderDTO order;
    private ProductDTO product;
    private Long count;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long id, OrderDTO order, ProductDTO product, Long count) {
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

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
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
        OrderItemDTO that = (OrderItemDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(order, that.order) && Objects.equals(product, that.product) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, product, count);
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "id=" + id +
                ", order=" + order.getId() +
                ", product=" + product.getName() +
                ", count=" + count +
                '}';
    }
}
