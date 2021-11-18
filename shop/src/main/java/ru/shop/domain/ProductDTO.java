package ru.shop.domain;


import java.util.Objects;

public class ProductDTO {
    private Long id;
    private CategoryDTO category;
    private String name;
    private String description;
    private Double price;
    private boolean active;

    public ProductDTO() {
    }

    public ProductDTO(Long id, CategoryDTO category, String name, String description, Double price, boolean active) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return active == that.active && Objects.equals(id, that.id) && Objects.equals(category, that.category) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, name, description, price, active);
    }

    @Override
    public String
    toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", active=" + active +
                '}';
    }
}
