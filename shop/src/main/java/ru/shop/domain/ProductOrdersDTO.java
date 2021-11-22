package ru.shop.domain;

import java.util.Objects;

public class ProductOrdersDTO {
    private Long id;
    private ProductDTO product;
    private Long productCount;

    public ProductOrdersDTO() {
    }

    public ProductOrdersDTO(Long id, ProductDTO product, Long productCount) {
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
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
        ProductOrdersDTO that = (ProductOrdersDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(productCount, that.productCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productCount);
    }

    @Override
    public String toString() {
        return "ProductOrdersDTO{" +
                "id=" + id +
                ", product=" + product.getName() +
                ", productCount=" + productCount +
                '}';
    }
}
