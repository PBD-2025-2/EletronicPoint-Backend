package pbd.ponto_eletronico.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pbd.ponto_eletronico.dto.RosterDTO;
import pbd.ponto_eletronico.request.RosterPostRequest;
import pbd.ponto_eletronico.service.RosterService;

import java.util.List;

@RequestMapping(path = "/roster")
@RestController
@RequiredArgsConstructor
public class RosterController {

    private final RosterService rosterService;

    @GetMapping
    public ResponseEntity<List<RosterDTO>> findAll(){
        return ResponseEntity.ok(rosterService.findAll());
    }

    @PostMapping
    public ResponseEntity<RosterDTO> registerSchedule(@RequestBody @Valid RosterPostRequest rosterPostRequest) throws JsonProcessingException {
        return ResponseEntity.ok(rosterService.save(rosterPostRequest));
    }
}
