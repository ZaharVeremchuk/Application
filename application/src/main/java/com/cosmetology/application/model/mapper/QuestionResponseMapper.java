package com.cosmetology.application.model.mapper;

import com.cosmetology.application.dto.response.QuestionsDTO;
import com.cosmetology.application.model.question.Question;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class QuestionResponseMapper  extends AbstractConverter<Question, QuestionsDTO> {
    @Override
    protected QuestionsDTO convert(Question question) {
        return QuestionsDTO.builder()
                .id(question.getId())
                .description(question.getDescription())
                .build();
    }
}
