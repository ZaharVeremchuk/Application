package com.cosmetology.application.model.mapper;

import com.cosmetology.application.dto.request.VisitRequestDTO;
import com.cosmetology.application.model.visits.Visit;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class VisitReqDTOToVisitMapper extends AbstractConverter<VisitRequestDTO, Visit> {

    @Override
    protected Visit convert(VisitRequestDTO visitRequestDTO) {
        return Visit.builder()
                .id(visitRequestDTO.getId())
                .procedure(visitRequestDTO.getProcedure())
                .startTime(visitRequestDTO.getStartTime())
                .finishTime(visitRequestDTO.getFinishTime())
                .additional_information(visitRequestDTO.getAdditional_information())
                .state_after_procedure(visitRequestDTO.getState_after_procedure())
                .reccomended_cosmetics(visitRequestDTO.getReccomended_cosmetics())
                .build();
    }
}
