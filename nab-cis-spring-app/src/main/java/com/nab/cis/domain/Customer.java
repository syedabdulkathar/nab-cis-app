package com.nab.cis.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "middle_name", length = 50)
    private String middleName;

    @NotNull
    @Size(max=50)
    @Column(name = "surname", length = 50)
    private String surName;

    @Size(max = 3)
    @Column(name = "initial", length = 3)
    private String initial;

    @Size(max = 10)
    @Column(name = "title", length = 10)
    private String title;

    @NotNull
    @Size(max = 20)
    @Column(name = "sex")
    private String sex;

    @Size(max = 30)
    @Column(name = "martial_status", length = 30)
    private String martialStatus;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "credit_rating", precision = 3, nullable = false)
    private Integer creditRating;

    @NotNull
    @Column(name = "nab_customer", nullable = false)
    private boolean nabCustomer;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Address.class)
    private List<Address> addresses;

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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        for (Address address:addresses) {
            addAddress(address);
        }
    }

    public void addAddress(Address address) {
        if(this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        this.addresses.add(address);
        address.setCustomer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surName='" + surName + '\'' +
                ", initial='" + initial + '\'' +
                ", title='" + title + '\'' +
                ", sex='" + sex + '\'' +
                ", martialStatus='" + martialStatus + '\'' +
                ", creditRating=" + creditRating +
                ", nabCustomer=" + nabCustomer +
                '}';
    }

}
