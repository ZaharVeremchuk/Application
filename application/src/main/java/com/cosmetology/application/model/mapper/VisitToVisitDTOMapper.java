package com.cosmetology.application.model.mapper;

import com.cosmetology.application.dto.response.VisitDTO;
import com.cosmetology.application.model.visits.Visit;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class VisitToVisitDTOMapper extends AbstractConverter<Visit, VisitDTO> {

    @Override
    protected VisitDTO convert(Visit visit) {
        return VisitDTO
                .builder()
                .visitId(visit.getId())
                .procedure(visit.getProcedure())
                .startTime(visit.getStartTime())
                .finishTime(visit.getFinishTime())
                .reccomended_cosmetics(visit.getReccomended_cosmetics())
                .state_after_procedure(visit.getState_after_procedure())
                .additional_information(visit.getAdditional_information())
                .build();
    }
}
