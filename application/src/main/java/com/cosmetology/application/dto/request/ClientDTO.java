package com.cosmetology.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Builder
@Getter
@Setter
public class ClientDTO {

    private Long id;

    @NotBlank
    @Size(max = 255)
    private String fullName;

    private LocalDate birthday;

    @Size(max = 255)
    private String workplace;

    private String homeСare;

    private String chronicDiseases;

    private String healingWounds;

    private String allergicReactions;

    private String alcoholSmoking;

    private String whatUseBefore;

    private String spf;

}
