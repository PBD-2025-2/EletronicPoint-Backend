package pbd.ponto_eletronico.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pbd.ponto_eletronico.dto.EletronicPointsArchiveDTO;
import pbd.ponto_eletronico.enums.EletronicPointArchiveStatus;
import pbd.ponto_eletronico.service.EletronicPointsArchiveService;

import java.util.List;

@RestController
@RequestMapping("api/v1/eletronicPointsArchive")
@RequiredArgsConstructor
public class EletronicPointsArchiveController {
    private final EletronicPointsArchiveService eletronicPointsArchiveService;

    @GetMapping
    public ResponseEntity<List<EletronicPointsArchiveDTO>> findAll() {
        return ResponseEntity.ok(eletronicPointsArchiveService.findAll());
    }

    @GetMapping(path = "id/{id}")
    public ResponseEntity<EletronicPointsArchiveDTO> findById(Long id) {
        return ResponseEntity.ok(eletronicPointsArchiveService.findById(id));
    }

    @GetMapping(path = "fileBatch/{fileBatch}")
    public ResponseEntity<List<EletronicPointsArchiveDTO>> findByFileBatch(String fileBatch) {
        return ResponseEntity.ok(eletronicPointsArchiveService.findByFileBatch(fileBatch));
    }

    @GetMapping(path = "statusArchive/{statusArchive}")
    public ResponseEntity<List<EletronicPointsArchiveDTO>> findByFileBatch(EletronicPointArchiveStatus statusArchive) {
        return ResponseEntity.ok(eletronicPointsArchiveService.findByStatusArchive(statusArchive));
    }

    @PostMapping(path = "/file", consumes = "multipart/form-data")
    public ResponseEntity<List<EletronicPointsArchiveDTO>> importArchive(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(eletronicPointsArchiveService.importArchive(file));
    }
}
