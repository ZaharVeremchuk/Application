package com.cosmetology.application.service;

import com.cosmetology.application.dto.request.GeneralDTO;
import com.cosmetology.application.model.aswers.Answer;
import com.cosmetology.application.model.client.Client;

import com.cosmetology.application.model.complaint.Complaint;
import com.cosmetology.application.model.question.Question;
import com.cosmetology.application.repository.AnswerRepository;
import com.cosmetology.application.repository.ClientRepository;
import com.cosmetology.application.repository.ComplaintRepository;
import com.cosmetology.application.repository.QuestionRepository;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GeneralService {

    private ClientRepository clientRepository;

    private AnswerRepository answerRepository;

    private QuestionRepository questionRepository;

    private ComplaintRepository complaintRepository;

    private ModelMapper modelMapper;

    public GeneralService(ClientRepository clientRepository, AnswerRepository answerRepository,
                          QuestionRepository questionRepository, ComplaintRepository complaintRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.complaintRepository = complaintRepository;
        this.modelMapper = modelMapper;
    }

    public void saveClientInformation(GeneralDTO generalDTO) {
        Client client = modelMapper.map(generalDTO.getClientDTO(), Client.class);
        Client savedClient = clientRepository.save(client);


        if (savedClient == null) {
            try {
                throw new NotFoundException("User was not saved");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        if (generalDTO.getComplaint().length() > 0) {
            Complaint complaint = new Complaint();
            complaint.setDescription(generalDTO.getComplaint());
            complaint.setClient(savedClient);
            complaintRepository.save(complaint);
        }

        generalDTO.getAnswerOnQuestionDTO().forEach(
                elem -> {
                    Question question = questionRepository.findById(elem.getQuestionId()).orElse(null);

                    Answer answer = new Answer();
                    answer.setQuestion(question);
                    answer.setClient(savedClient);
                    answer.setAnswer(elem.getAnswer());
                    answerRepository.save(answer);
                }
        );
    }
}
