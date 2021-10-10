package com.cosmetology.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VisitDTO {

    private Long visitId;

    private String procedure;

    private LocalDateTime startTime;

    private LocalDateTime finishTime;

    private String additional_information;

    private String state_after_procedure;

    private String reccomended_cosmetics;

}
