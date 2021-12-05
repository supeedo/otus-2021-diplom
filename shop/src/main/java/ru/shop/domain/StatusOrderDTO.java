package ru.shop.domain;

import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
public class StatusOrderDTO {
    private Long id;
    private String status;

    public StatusOrderDTO() {
    }

    public StatusOrderDTO(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusOrderDTO that = (StatusOrderDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public String toString() {
        return "StatusOrderDTO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
