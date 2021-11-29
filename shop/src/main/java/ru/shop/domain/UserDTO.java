package ru.shop.domain;

import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private UserInformationDTO userInformation;
    private boolean active;
    private RoleDTO role;

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String password, UserInformationDTO userInformation, RoleDTO role, boolean active) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userInformation = userInformation;
        this.active = active;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInformationDTO getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformationDTO userInformation) {
        this.userInformation = userInformation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return active == userDTO.active && Objects.equals(id, userDTO.id) && Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, active);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userInformation=" + userInformation +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}
