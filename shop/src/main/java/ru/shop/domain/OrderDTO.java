package ru.shop.domain;

import java.util.List;
import java.util.Objects;

public class OrderDTO {
    private Long id;
    private UserDTO user;
    private String note;
    private StatusOrderDTO status;
    private List<ProductOrderDTO> productOrders;

    public OrderDTO() {
    }

    public OrderDTO(Long id, UserDTO user, String note, StatusOrderDTO status, List<ProductOrderDTO> productOrders) {
        this.id = id;
        this.user = user;
        this.note = note;
        this.status = status;
        this.productOrders = productOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public List<ProductOrderDTO> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrderDTO> productOrders) {
        this.productOrders = productOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) && Objects.equals(user, orderDTO.user) && Objects.equals(note, orderDTO.note) && Objects.equals(status, orderDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, note, status);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", user=" + user.toString() +
                ", note='" + note + '\'' +
                ", status=" + status.getStatus() +
                '}';
    }
}
