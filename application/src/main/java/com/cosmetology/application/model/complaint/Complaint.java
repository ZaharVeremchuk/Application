package com.cosmetology.application.model.complaint;

import com.cosmetology.application.model.client.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(exclude = "client")
@ToString(exclude = {"client"})
@NoArgsConstructor
@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_client",nullable = false)
    private Client client;
}
