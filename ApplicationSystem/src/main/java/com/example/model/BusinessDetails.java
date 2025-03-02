package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "BUSINESS_DETAILS")
public class BusinessDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTACT_DETAILS")
    private String contactDetails;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "INDUSTRY")
    private String industry;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "FINANCIAL_INFORMATION")
    private String financialInformation;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

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

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFinancialInformation() {
        return financialInformation;
    }

    public void setFinancialInformation(String financialInformation) {
        this.financialInformation = financialInformation;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
