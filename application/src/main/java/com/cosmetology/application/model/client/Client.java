package com.cosmetology.application.model.client;

import com.cosmetology.application.model.aswers.Answer;
import com.cosmetology.application.model.complaint.Complaint;
import com.cosmetology.application.model.visits.Visit;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"answers","complaintList","visits"})
@ToString(exclude = {"answers","complaintList","visits"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String fullName;

    private LocalDate birthday;

    @Size(max = 255)
    private String workplace;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Answer> answers;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Complaint> complaintList;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Visit> visits;

}
