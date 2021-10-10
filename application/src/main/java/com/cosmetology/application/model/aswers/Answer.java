package com.cosmetology.application.model.aswers;

import com.cosmetology.application.model.client.Client;
import com.cosmetology.application.model.question.Question;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"question","client"})
@ToString(exclude = {"question","client"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_question", nullable = false)
    private Question question;

    private String answer;
}
