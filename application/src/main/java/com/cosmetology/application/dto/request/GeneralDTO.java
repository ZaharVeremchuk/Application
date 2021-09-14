package com.cosmetology.application.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class GeneralDTO {

    @NotBlank
    private ClientDTO clientDTO;

    @NotBlank
    private List<AnswerOnQuestionDTO> answerOnQuestionDTO;

    private String complaint;

}
