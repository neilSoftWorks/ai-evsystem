package com.example.listener;

import com.example.event.BusinessDetailsDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BusinessDetailsDeletedEventListener {

    @EventListener
    public void handleBusinessDetailsDeletedEvent(BusinessDetailsDeletedEvent event) {
        System.out.println("EVENT - Business details deleted: " + event.getBusinessDetails().getId());
    }
}
