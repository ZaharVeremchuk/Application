package com.cosmetology.application.repository;

import  com.cosmetology.application.model.visits.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {

    List<Visit> findVisitByClient_Id(Long client_id);

}
