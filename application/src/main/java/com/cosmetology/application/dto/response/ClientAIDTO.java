package com.cosmetology.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Setter
@Builder
public class ClientAIDTO {

    private Long id;

    private String fullName;

    private LocalDate birthday;

    private String workplace;

    private String homeСare;

    private String chronicDiseases;

    private String healingWounds;

    private String allergicReactions;

    private String alcoholSmoking;

    private String whatUseBefore;

    private String spf;

    private List<String> complaints;

    private Map<String,String> answersList;

}

