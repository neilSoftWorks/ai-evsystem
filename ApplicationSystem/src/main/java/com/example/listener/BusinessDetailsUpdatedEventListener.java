package com.example.listener;

import com.example.event.BusinessDetailsUpdatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BusinessDetailsUpdatedEventListener {

    @EventListener
    public void handleBusinessDetailsUpdatedEvent(BusinessDetailsUpdatedEvent event) {
        System.out.println("EVENT - Business details updated: " + event.getBusinessDetails().getId());
    }
}
