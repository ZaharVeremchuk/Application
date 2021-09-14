package com.cosmetology.application.model.client;

import com.cosmetology.application.model.aswers.Answer;
import com.cosmetology.application.model.complaint.Complaint;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"answers","complaintList"})
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

    @Column(name = "home_care")
    private String homeСare;

    @Column(name = "chronic_diseases")
    private String chronicDiseases;

    @Column(name = "healing_wounds")
    private String healingWounds;

    @Column(name = "allergic_reactions")
    private String allergicReactions;

    @Column(name = "alcohol_smoking")
    private String alcoholSmoking;

    @Column(name = "what_use_before")
    private String whatUseBefore;

    private String spf;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Answer> answers;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Complaint> complaintList;

}
