package com.cosmetology.application.repository;

import com.cosmetology.application.model.complaint.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {

    @Query("select c from Complaint c where c.client.id = :clientId")
    List<String> findComplaintByClient(Long clientId);

}
