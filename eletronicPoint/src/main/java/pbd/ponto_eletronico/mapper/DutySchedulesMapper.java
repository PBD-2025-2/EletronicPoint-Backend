package pbd.ponto_eletronico.mapper;

import org.mapstruct.Mapper;
import pbd.ponto_eletronico.dto.DutySchedulesDTO;
import pbd.ponto_eletronico.entity.DutySchedules;

@Mapper(componentModel = "spring")
public interface DutySchedulesMapper {
    DutySchedules dutySchedulesDTOToDutySchedules(DutySchedulesDTO dutySchedulesDTO);
    DutySchedulesDTO dutySchedulesTodutySchedulesDTO(DutySchedules dutySchedules);
}
