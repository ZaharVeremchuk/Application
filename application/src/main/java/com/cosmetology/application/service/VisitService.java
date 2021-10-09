package com.cosmetology.application.service;

import com.cosmetology.application.dto.request.VisitRequestDTO;
import com.cosmetology.application.dto.response.VisitDTO;
import com.cosmetology.application.model.client.Client;
import com.cosmetology.application.model.visits.Visit;
import com.cosmetology.application.exception.constant.ExceptionConstant;
import com.cosmetology.application.exception.exceptions.ClientNotFound;
import com.cosmetology.application.repository.ClientRepository;
import com.cosmetology.application.repository.VisitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {

    private VisitRepository visitRepository;
    private ClientRepository clientRepository;
    private ModelMapper modelMapper;

    public VisitService(VisitRepository visitRepository, ClientRepository clientRepository, ModelMapper modelMapper) {
        this.visitRepository = visitRepository;
        this.modelMapper = modelMapper;
    }

    public List<VisitDTO> searchVisitsByClientId(Long clientId) {
        if (clientId == null) {
            throw new ClientNotFound(ExceptionConstant.CLIENT_NOT_FOUND + clientId);
        }
        List<VisitDTO> visitDTOList = visitRepository.findVisitByClient_Id(clientId)
                .stream()
                .map(v -> modelMapper.map(v, VisitDTO.class))
                .collect(Collectors.toList());
        return visitDTOList;
    }

    public ResponseEntity<?> save(VisitRequestDTO visitRequestDTO) {
        Client client = clientRepository.findById(visitRequestDTO.getId_client()).orElseThrow(() -> new ClientNotFound(ExceptionConstant.CLIENT_NOT_FOUND + visitRequestDTO.getId_client()));
        Visit visit = modelMapper.map(visitRequestDTO, Visit.class);
        visit.setClient(client);
        visitRepository.save(visit);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> delete(Long id) {
        visitRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
