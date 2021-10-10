package com.cosmetology.application.model.visits;

import com.cosmetology.application.model.client.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@EqualsAndHashCode(exclude = "client")
@ToString(exclude = "client")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Client client;

    private String procedure;

    private LocalDateTime startTime;

    private LocalDateTime finishTime;

    private String additional_information;

    private String state_after_procedure;

    private String reccomended_cosmetics;
}
