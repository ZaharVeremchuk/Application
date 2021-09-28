package com.cosmetology.application.service;

import com.cosmetology.application.dto.request.ClientDTO;
import com.cosmetology.application.dto.response.ClientAIDTO;
import com.cosmetology.application.exception.constant.ExceptionConstant;
import com.cosmetology.application.exception.exceptions.ClientBadRequest;
import com.cosmetology.application.exception.exceptions.ClientNotFound;
import com.cosmetology.application.model.client.Client;
import com.cosmetology.application.repository.ClientRepository;
import com.cosmetology.application.repository.ComplaintRepository;
import com.cosmetology.application.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private QuestionRepository questionRepository;
    private ComplaintRepository complaintRepository;

    private ModelMapper modelMapper;

    public ClientService(ClientRepository clientRepository, QuestionRepository questionRepository,
                         ComplaintRepository complaintRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.questionRepository = questionRepository;
        this.complaintRepository = complaintRepository;
        this.modelMapper = modelMapper;
    }

    public ClientDTO saveClient(ClientDTO clientDTO) {
        if (clientDTO.getId() != null) {
            throw new ClientBadRequest(ExceptionConstant.CLIENT_BAD_REQUEST + clientDTO.getId());
        }
        Client client = modelMapper.map(clientDTO, Client.class);
        client = clientRepository.save(client);
        ClientDTO responseDto = modelMapper.map(client, ClientDTO.class);
        return responseDto;
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFound(ExceptionConstant.QUESTION_NOT_FOUND));
        clientRepository.delete(client);
    }

    public List<ClientDTO> getAllClients() {
        List<ClientDTO> clientDTOList = clientRepository
                .findAll()
                .stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
        return clientDTOList;
    }

    public List<ClientAIDTO> getAllInfoAboutClient() {
        List<ClientAIDTO> clientDTOS = clientRepository.getAllInfoAboutClient().stream().map(
                client -> {
                    ClientAIDTO clientAIDTO = modelMapper.map(client, ClientAIDTO.class);
                    clientAIDTO.setAnswersList(questionRepository.getQuestionAndAnswersByClient(client.getId()));
                    clientAIDTO.setComplaints(complaintRepository.findComplaintByClient(client.getId()));
                    return clientAIDTO;
                }
        ).collect(Collectors.toList());
        return clientDTOS;
    }

}
