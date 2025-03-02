package com.example.service;

import com.example.event.BusinessDetailsCreatedEvent;
import com.example.event.BusinessDetailsDeletedEvent;
import com.example.event.BusinessDetailsUpdatedEvent;
import com.example.model.ApplicationStatus;
import com.example.model.BusinessDetails;
import com.example.repository.ApplicationStatusRepository;
import com.example.repository.BusinessDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

    @Autowired
    private ApplicationStatusRepository applicationStatusRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public BusinessDetails submitApplication(BusinessDetails businessDetails) {
        BusinessDetails savedBusinessDetails = businessDetailsRepository.save(businessDetails);
        ApplicationStatus initialStatus = new ApplicationStatus();
        initialStatus.setStatusName("Submitted");
        initialStatus.setBusinessDetails(savedBusinessDetails);
        applicationStatusRepository.save(initialStatus);
        applicationEventPublisher.publishEvent(new BusinessDetailsCreatedEvent(this, savedBusinessDetails));
        return savedBusinessDetails;
    }

    public List<BusinessDetails> getAllBusinessDetails() {
        List<BusinessDetails> details = businessDetailsRepository.findAll();
        for (BusinessDetails detail : details) {
            logger.info("Retrieved business detail: id={}, name='{}', contactDetails='{}', phoneNumber='{}', emailAddress='{}', address='{}'",
                    detail.getId(), detail.getName(), detail.getContactDetails(), detail.getPhoneNumber(), detail.getEmailAddress(), detail.getAddress());
        }
        return details;
    }

    public BusinessDetails getBusinessDetailsById(Long id) {
        Optional<BusinessDetails> optionalBusinessDetails = businessDetailsRepository.findById(id);
        return optionalBusinessDetails.orElse(null);
    }

    @Transactional
    public BusinessDetails updateBusinessDetails(Long id, BusinessDetails updatedBusinessDetails) {
        Optional<BusinessDetails> existingBusinessDetails = businessDetailsRepository.findById(id);
        if (existingBusinessDetails.isPresent()) {
            updatedBusinessDetails.setId(id);
            BusinessDetails savedBusinessDetails = businessDetailsRepository.save(updatedBusinessDetails);

            ApplicationStatus updateStatus = new ApplicationStatus();
            updateStatus.setStatusName("Updated");
            updateStatus.setBusinessDetails(savedBusinessDetails);
            applicationStatusRepository.save(updateStatus);

            applicationEventPublisher.publishEvent(new BusinessDetailsUpdatedEvent(this, savedBusinessDetails));

            return savedBusinessDetails;
        }
        return null;
    }

    @Transactional
    public void deleteBusinessDetails(Long id) {
        Optional<BusinessDetails> businessDetailsOptional = businessDetailsRepository.findById(id);
        businessDetailsOptional.ifPresent(businessDetails -> {
            List<ApplicationStatus> applicationStatuses = applicationStatusRepository.findByBusinessDetails(businessDetails);
            applicationStatusRepository.deleteAll(applicationStatuses);
            businessDetailsRepository.deleteById(id);
            applicationEventPublisher.publishEvent(new BusinessDetailsDeletedEvent(this, businessDetails));
        });
    }
}
