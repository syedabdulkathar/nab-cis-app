package com.nab.cis.service.dto;

import java.util.List;

public class StateDTO extends AuditDTO {
    private Long id;
    private String name;
    private CountryDTO country;
   // private List<CityDTO> cities;

    public StateDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryDTO getCountry() {
        return this.country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

//    public List<CityDTO> getCities() {
//        return cities;
//    }
//
//    public void setCities(List<CityDTO> cities) {
//        this.cities = cities;
//    }
}
