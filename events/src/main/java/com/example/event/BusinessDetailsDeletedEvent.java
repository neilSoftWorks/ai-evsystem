package com.example.event;

import com.example.model.BusinessDetails;
import org.springframework.context.ApplicationEvent;

public class BusinessDetailsDeletedEvent extends ApplicationEvent {

    private BusinessDetails businessDetails;

    public BusinessDetailsDeletedEvent(Object source, BusinessDetails businessDetails) {
        super(source);
        this.businessDetails = businessDetails;
    }

    public BusinessDetails getBusinessDetails() {
        return businessDetails;
    }
}
