package com.nab.cis.service.dto;

import com.nab.cis.domain.Address;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class CustomerDTO extends AuditDTO {

    private Long id;
    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String surName;
    private String initial;
    private String title;
    @NotBlank
    private String sex;
    private String martialStatus;
    private Integer creditRating = 0;
    private boolean nabCustomer = false;
    private List<AddressDTO> addresses;

    public CustomerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }

    public Integer getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(Integer creditRating) {
        this.creditRating = creditRating;
    }

    public boolean isNabCustomer() {
        return nabCustomer;
    }

    public void setNabCustomer(boolean nabCustomer) {
        this.nabCustomer = nabCustomer;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
}
