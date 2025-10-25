package pbd.ponto_eletronico.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pbd.ponto_eletronico.dto.RosterDTO;
import pbd.ponto_eletronico.request.RosterDiaryPostRequest;
import pbd.ponto_eletronico.request.RosterDiaryPutRequest;
import pbd.ponto_eletronico.request.RosterDutyPostRequest;
import pbd.ponto_eletronico.request.RosterDutyPutRequest;
import pbd.ponto_eletronico.service.RosterService;

import java.util.List;

@RequestMapping("api/v1/rosters")
@RestController
@RequiredArgsConstructor
public class RosterController {

    private final RosterService rosterService;

    @GetMapping
    public ResponseEntity<List<RosterDTO>> findAll() throws JsonProcessingException {
        return ResponseEntity.ok(rosterService.findAll());
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<RosterDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(rosterService.findById(id));
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<RosterDTO> findByName(@PathVariable String name){
        return ResponseEntity.ok(rosterService.findByName(name));
    }

    @PostMapping("/daily")
    public ResponseEntity<?> registerDailySchedule(@RequestBody @Valid RosterDiaryPostRequest rosterPostRequest){
        return new ResponseEntity<>(rosterService.save(rosterPostRequest), HttpStatus.CREATED);
    }

    @PostMapping("/duty")
    public ResponseEntity<?> registerDutySchedule(@RequestBody @Valid RosterDutyPostRequest rosterDutyPostRequest) {
        return new ResponseEntity<>(rosterService.save(rosterDutyPostRequest), HttpStatus.CREATED);
    }

    @PutMapping("/Diary/id{id}")
    public ResponseEntity<?> replaceDiary(@PathVariable Long id, @RequestBody @Valid RosterDiaryPutRequest rosterDiaryPutRequest){
        return new ResponseEntity<>(rosterService.replace(id, rosterDiaryPutRequest), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/Duty/id{id}")
    public ResponseEntity<?> replaceDuty(@PathVariable Long id, @RequestBody @Valid RosterDutyPutRequest rosterDutyPutRequest){
        return new ResponseEntity<>(rosterService.replace(id, rosterDutyPutRequest), HttpStatus.NO_CONTENT);
    }
}
