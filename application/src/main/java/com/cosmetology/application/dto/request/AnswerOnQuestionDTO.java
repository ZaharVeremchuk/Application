package com.cosmetology.application.dto.request;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
public class AnswerOnQuestionDTO {

    @Size(min = 1)
    private Long questionId;

    private String answer;

}
