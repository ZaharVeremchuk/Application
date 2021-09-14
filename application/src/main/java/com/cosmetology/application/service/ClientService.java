package com.cosmetology.application.service;

import com.cosmetology.application.constant.ApplicationConstant;
import com.cosmetology.application.dto.request.ClientDTO;
import com.cosmetology.application.dto.response.ClientAIDTO;
import com.cosmetology.application.model.client.Client;
import com.cosmetology.application.repository.ClientRepository;
import com.cosmetology.application.repository.ComplaintRepository;
import com.cosmetology.application.repository.QuestionRepository;
import javassist.NotFoundException;
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

    public ClientService(ClientRepository clientRepository, QuestionRepository questionRepository, ComplaintRepository complaintRepository) {
        this.clientRepository = clientRepository;
        this.questionRepository = questionRepository;
        this.complaintRepository = complaintRepository;
    }

    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        if (client == null) {

        }
        client = clientRepository.save(client);
        ClientDTO responseDto = modelMapper.map(client, ClientDTO.class);
        return responseDto;
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            try {
                throw new NotFoundException(ApplicationConstant.CLIENT_NOT_FOUND);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
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
