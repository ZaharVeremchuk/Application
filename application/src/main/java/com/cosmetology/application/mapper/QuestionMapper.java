package com.cosmetology.application.mapper;

import com.cosmetology.application.dto.response.QuestionsDTO;
import com.cosmetology.application.model.question.Question;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper extends AbstractConverter<QuestionsDTO, Question> {


    @Override
    protected Question convert(QuestionsDTO questionsDTO) {
        return Question.builder()
                .description(questionsDTO.getDescription())
                .build();
    }
}