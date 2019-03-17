package com.nab.cis.service.dto;

public class CityDTO extends AuditDTO {

    private Long id;
    private String name;
   private StateDTO state;

    public CityDTO() {
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

    public StateDTO getState(){
        return state;
    }
    public void setState(StateDTO state) {
        this.state = state;
    }
}
