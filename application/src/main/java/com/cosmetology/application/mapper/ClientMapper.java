package com.cosmetology.application.mapper;

import com.cosmetology.application.dto.request.ClientDTO;
import com.cosmetology.application.model.client.Client;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper extends AbstractConverter<ClientDTO, Client> {

    @Override
    protected Client convert(ClientDTO clientDTO) {
        return Client.builder()
                .fullName(clientDTO.getFullName())
                .birthday(clientDTO.getBirthday())
                .workplace(clientDTO.getWorkplace())
                .build();
    }
}
