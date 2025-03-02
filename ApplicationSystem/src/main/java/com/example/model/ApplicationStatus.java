package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "APPLICATION_STATUS")
public class ApplicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STATUS_NAME")
    private String statusName;

    @ManyToOne
    @JoinColumn(name = "BUSINESS_DETAILS_ID")
    private BusinessDetails businessDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public BusinessDetails getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(BusinessDetails businessDetails) {
        this.businessDetails = businessDetails;
    }
}
