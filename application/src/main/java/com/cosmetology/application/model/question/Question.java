package com.cosmetology.application.model.question;

import com.cosmetology.application.model.aswers.Answer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Data
@EqualsAndHashCode(exclude = "answers")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String description;

    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Answer> answers;

}
