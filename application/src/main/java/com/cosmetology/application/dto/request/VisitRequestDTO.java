package com.cosmetology.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitRequestDTO {

    private Long id;

    private Long id_client;

    private String procedure;

    private LocalDateTime startTime;

    private LocalDateTime finishTime;

    private String additional_information;

    private String state_after_procedure;

    private String reccomended_cosmetics;
}

