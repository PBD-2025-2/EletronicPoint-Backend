package pbd.ponto_eletronico.mapper;

import org.mapstruct.Mapper;
import pbd.ponto_eletronico.entity.Schedule;
import pbd.ponto_eletronico.dto.ScheduleDTO;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    Schedule scheduleDTOToSchedule(ScheduleDTO scheduleDTO);
    ScheduleDTO scheduleToScheduleDTO(Schedule schedule);
}
