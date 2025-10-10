package pbd.ponto_eletronico.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pbd.ponto_eletronico.dto.DailySchedulesDTO;
import pbd.ponto_eletronico.dto.DutySchedulesDTO;
import pbd.ponto_eletronico.dto.RosterDTO;
import pbd.ponto_eletronico.request.RosterDiaryPostRequest;
import pbd.ponto_eletronico.request.RosterDutyPostRequest;
import pbd.ponto_eletronico.service.RosterService;

import java.util.List;

@RequestMapping(path = "/type")
@RestController
@RequiredArgsConstructor
public class RosterController {

    private final RosterService rosterService;

    @GetMapping
    public ResponseEntity<List<RosterDTO>> findAll() throws JsonProcessingException {
        return ResponseEntity.ok(rosterService.findAll());
    }

    @PostMapping("/daily")
    public ResponseEntity<DailySchedulesDTO> registerDailySchedule(@RequestBody @Valid RosterDiaryPostRequest rosterPostRequest) throws JsonProcessingException {
        return new ResponseEntity<>(rosterService.registerDailySchedule(rosterPostRequest), HttpStatus.CREATED);
    }

    @PostMapping("/duty")
    public ResponseEntity<DutySchedulesDTO> registerDutySchedule(@RequestBody @Valid RosterDutyPostRequest rosterDutyPostRequest) throws JsonProcessingException {
        return new ResponseEntity<>(rosterService.registerDutySchedule(rosterDutyPostRequest), HttpStatus.CREATED);
    }
}
