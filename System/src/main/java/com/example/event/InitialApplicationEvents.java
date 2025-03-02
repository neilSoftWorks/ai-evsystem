package com.example.event;

import java.util.List;
import com.example.constants.ApplicationStatus;
import org.springframework.context.ApplicationEvent;

public class InitialApplicationEvents extends ApplicationEvent {

    private final List<ApplicationStatus> statuses;

    public InitialApplicationEvents(Object source, List<ApplicationStatus> statuses) {
        super(source);
        this.statuses = statuses;
    }

    public List<ApplicationStatus> getStatuses() {
        return statuses;
    }
}
