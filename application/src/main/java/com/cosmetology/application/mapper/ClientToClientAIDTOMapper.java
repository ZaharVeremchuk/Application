package com.cosmetology.application.mapper;

import com.cosmetology.application.dto.response.ClientAIDTO;
import com.cosmetology.application.model.client.Client;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class ClientToClientAIDTOMapper extends AbstractConverter<Client, ClientAIDTO> {

    @Override
    protected ClientAIDTO convert(Client client) {
        return ClientAIDTO.builder()
                .id(client.getId())
                .fullName(client.getFullName())
                .birthday(client.getBirthday())
                .workplace(client.getWorkplace())
                .build();
    }
}
