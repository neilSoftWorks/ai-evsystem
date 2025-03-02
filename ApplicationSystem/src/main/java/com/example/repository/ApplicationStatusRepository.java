package com.example.repository;

import com.example.model.ApplicationStatus;
import com.example.model.BusinessDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Long> {
    List<ApplicationStatus> findByBusinessDetails(BusinessDetails businessDetails);
}
