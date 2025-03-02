package com.example.rules;

import com.example.constants.ApplicationStatus;
import com.example.event.InitialApplicationEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationRules {

    @Autowired
    private KafkaTemplate<String, InitialApplicationEvents> kafkaTemplate;

    private static final String TOPIC = "application-initial-events";

    public List<ApplicationStatus> getInitialEvents(String applicationType) {
        List<ApplicationStatus> events = new ArrayList<>();
        if ("EV_FUEL_CARD".equals(applicationType)) {
            events.add(ApplicationStatus.KYC_PENDING);
            events.add(ApplicationStatus.AML_PENDING);
            events.add(ApplicationStatus.CREDIT_CHECK_PENDING);
        }
        kafkaTemplate.send(TOPIC, new InitialApplicationEvents(this, events));
        return events;
    }
}
