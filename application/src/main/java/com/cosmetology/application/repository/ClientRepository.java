package com.cosmetology.application.repository;

import com.cosmetology.application.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query("select c from Client c  " +
            "inner join Complaint cm " +
            "on c.id = cm.client.id " +
            "inner join Answer a  " +
            "on c.id = a.client.id")
    List<Client> getAllInfoAboutClient();
}
