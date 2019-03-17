package com.nab.cis.service.dto;

import com.nab.cis.domain.State;

import java.util.List;

public class CountryDTO extends AuditDTO {
    private Long id;
    private String name;
    //private List<StateDTO> states;

    public CountryDTO() {
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

//    public List<StateDTO> getStates() {
//        return states;
//    }

//    public void setStates(List<StateDTO> states) {
//        this.states = states;
//    }
}
