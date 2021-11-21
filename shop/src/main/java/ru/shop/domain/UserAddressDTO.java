package ru.shop.domain;

import java.util.Objects;

public class UserAddressDTO {
    private Long id;
    private String city;
    private String street;
    private String houseNumber;
    private Integer floor;
    private String apartmentNumber;

    public UserAddressDTO() {
    }

    public UserAddressDTO(Long id, String city, String street, String houseNumber, Integer floor, String apartmentNumber) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.floor = floor;
        this.apartmentNumber = apartmentNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAddressDTO that = (UserAddressDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(city, that.city) && Objects.equals(street, that.street) && Objects.equals(houseNumber, that.houseNumber) && Objects.equals(floor, that.floor) && Objects.equals(apartmentNumber, that.apartmentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, houseNumber, floor, apartmentNumber);
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", floor=" + floor +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                '}';
    }
}
