package ru.shop.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Validated
public class OrderDTO {
    @NotNull
    private Long id;
    @NotNull
    private UserDTO user;
    @Length(max = 2048)
    private String note;
    @NotNull
    private StatusOrderDTO status;
    @NotNull
    @Length(min = 1)
    private List<OrderItemDTO> ordersItems;

    public OrderDTO() {
    }

    public OrderDTO(Long id, UserDTO user, String note, StatusOrderDTO status, List<OrderItemDTO> productOrders) {
        this.id = id;
        this.user = user;
        this.note = note;
        this.status = status;
        this.ordersItems = productOrders;
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

    public List<OrderItemDTO> getOrdersItems() {
        return ordersItems;
    }

    public void setOrdersItems(List<OrderItemDTO> ordersItems) {
        this.ordersItems = ordersItems;
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
