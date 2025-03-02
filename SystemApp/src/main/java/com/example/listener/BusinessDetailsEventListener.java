package com.example.listener;

import com.example.event.BusinessDetailsCreatedEvent;
import com.example.event.BusinessDetailsUpdatedEvent;
import com.example.event.BusinessDetailsDeletedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BusinessDetailsEventListener {

    @KafkaListener(topics = "business-details-created", groupId = "system-app-group")
    public void handleCreatedEvent(BusinessDetailsCreatedEvent event) {
        System.out.println("SystemApp: Business details created: " + event.getBusinessDetails().getId());
        // Add your logic to process the created event
    }

    @KafkaListener(topics = "business-details-updated", groupId = "system-app-group")
    public void handleUpdatedEvent(BusinessDetailsUpdatedEvent event) {
        System.out.println("SystemApp: Business details updated: " + event.getBusinessDetails().getId());
        // Add your logic to process the updated event
    }

    @KafkaListener(topics = "business-details-deleted", groupId = "system-app-group")
    public void handleDeletedEvent(BusinessDetailsDeletedEvent event) {
        System.out.println("SystemApp: Business details deleted: " + event.getBusinessDetails().getId());
        // Add your logic to process the deleted event
    }
}