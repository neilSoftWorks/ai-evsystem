package com.example.listener;

import com.example.event.BusinessDetailsCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BusinessDetailsCreatedEventListener {

    @EventListener
    public void handleBusinessDetailsCreatedEvent(BusinessDetailsCreatedEvent event) {
        System.out.println("EVENT - Business details created: " + event.getBusinessDetails().getId());
    }
}
