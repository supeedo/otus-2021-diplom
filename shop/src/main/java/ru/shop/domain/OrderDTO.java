package ru.shop.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrderDTO {

    private Long id;

    private Date createTime;

    private Date deliveryTime;

    private String note;

    private Long statusId;

    private UserDTO user;

    private List<ProductDTO> products;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Date createTime, Date deliveryTime, String note, Long statusId, UserDTO user, List<ProductDTO> products) {
        this.id = id;
        this.createTime = createTime;
        this.deliveryTime = deliveryTime;
        this.note = note;
        this.statusId = statusId;
        this.user = user;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) && Objects.equals(createTime, orderDTO.createTime) && Objects.equals(deliveryTime, orderDTO.deliveryTime) && Objects.equals(note, orderDTO.note) && Objects.equals(statusId, orderDTO.statusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, deliveryTime, note, statusId);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", deliveryTime=" + deliveryTime +
                ", note='" + note + '\'' +
                ", statusId=" + statusId +
                '}';
    }
}
