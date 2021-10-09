package com.cosmetology.application.dto.response;

import com.cosmetology.application.model.client.Client;

import java.time.LocalDateTime;

public class VisitDTO {

    private Client client;

    private String procedure;

    private LocalDateTime startTime;

    private LocalDateTime finishTime;

    private String additional_information;

    private String state_after_procedure;

    private String reccomended_cosmetics;

}
