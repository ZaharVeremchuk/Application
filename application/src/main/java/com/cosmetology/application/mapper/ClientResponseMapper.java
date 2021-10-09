package com.cosmetology.application.mapper;

import com.cosmetology.application.dto.request.ClientDTO;
import com.cosmetology.application.model.client.Client;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class ClientResponseMapper extends AbstractConverter<Client, ClientDTO> {
    @Override
    protected ClientDTO convert(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .fullName(client.getFullName())
                .birthday(client.getBirthday())
                .workplace(client.getWorkplace())
                .build();
    }
}
