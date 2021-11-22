package ru.shop.domain;


import java.util.List;
import java.util.Objects;

public class OrderDTO {

    private Long id;

    private String note;

    private StatusOrderDTO status;

    private UserDTO user;

    private List<ProductOrdersDTO> products;

    public OrderDTO() {
    }

    public OrderDTO(Long id,
                    String note,
                    StatusOrderDTO status,
                    UserDTO user,
                    List<ProductOrdersDTO> products) {
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

    public StatusOrderDTO getStatus() {
        return status;
    }

    public void setStatus(StatusOrderDTO status) {
        this.status = status;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<ProductOrdersDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrdersDTO> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) && Objects.equals(note, orderDTO.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", statusId=" + status.getStatus() +
                '}';
    }
}
