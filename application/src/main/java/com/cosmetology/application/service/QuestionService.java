package com.cosmetology.application.service;

import com.cosmetology.application.dto.response.QuestionsDTO;
import com.cosmetology.application.exception.constant.ExceptionConstant;
import com.cosmetology.application.exception.exceptions.QuestionBadRequest;
import com.cosmetology.application.exception.exceptions.QuestionNotFound;
import com.cosmetology.application.model.question.Question;
import com.cosmetology.application.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    private ModelMapper modelMapper;


    public QuestionService(QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    public List<QuestionsDTO> allQuestions(){
        List<Question> questionList = questionRepository.findAll();
        List<QuestionsDTO> questionsDTOS = new ArrayList<>();
        questionList.forEach(
                question -> questionsDTOS.add(
                        QuestionsDTO.builder()
                                .id(question.getId())
                                .description(question.getDescription())
                        .build())
        );
        return questionsDTOS;
    }

    public QuestionsDTO saveQuestion(QuestionsDTO questionsDTO){
        if (questionsDTO.getId() != null){
            throw new QuestionBadRequest(ExceptionConstant.QUESTION_BAD_REQUEST);
        }
        Question question = modelMapper.map(questionsDTO,Question.class);
        Question response = questionRepository.save(question);
        questionsDTO = modelMapper.map(response,QuestionsDTO.class);
        return questionsDTO;
    }

    public void deleteQuestion(Long id){
        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionNotFound(ExceptionConstant.QUESTION_NOT_FOUND+ id));
        questionRepository.delete(question);
    }

}
