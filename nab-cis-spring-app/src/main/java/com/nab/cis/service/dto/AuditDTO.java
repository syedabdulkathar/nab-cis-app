package com.nab.cis.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class AuditDTO implements Serializable {
    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private Timestamp createdDate;
    @JsonIgnore
    private String lastModifiedNy;
    @JsonIgnore
    private Timestamp lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate =  createdDate;
    }

    public String getLastModifiedNy() {
        return lastModifiedNy;
    }

    public void setLastModifiedNy(String lastModifiedNy) {
        this.lastModifiedNy = lastModifiedNy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
