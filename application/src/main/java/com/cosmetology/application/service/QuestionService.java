package com.cosmetology.application.service;

import com.cosmetology.application.constant.ApplicationConstant;
import com.cosmetology.application.dto.response.QuestionsDTO;
import com.cosmetology.application.model.question.Question;
import com.cosmetology.application.repository.QuestionRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    private ModelMapper modelMapper;


    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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
        Question question = modelMapper.map(questionsDTO,Question.class);
        Question response = questionRepository.save(question);
        questionsDTO = modelMapper.map(response,QuestionsDTO.class);
        return questionsDTO;
    }

    public void deleteQuestion(Long id)  {
        Question question = questionRepository.findById(id).orElse(null);
        if (question == null){
            try {
                throw new NotFoundException(ApplicationConstant.QUESTION_NOT_FOUND);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        questionRepository.delete(question);
    }

}
