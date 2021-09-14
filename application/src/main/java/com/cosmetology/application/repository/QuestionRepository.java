package com.cosmetology.application.repository;

import com.cosmetology.application.model.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query("select q.description, a.answer from Question q inner join Answer a " +
            "on q.id = a.question.id where a.client.id = :clientId")
    Map<String,String> getQuestionAndAnswersByClient(Long clientId);
}
