package pbd.ponto_eletronico.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pbd.ponto_eletronico.dto.EletronicPointsArchiveDTO;
import pbd.ponto_eletronico.entity.EletronicPointsArchive;
import pbd.ponto_eletronico.entity.EmployeesRoles;
import pbd.ponto_eletronico.enums.EletronicPointArchiveStatus;
import pbd.ponto_eletronico.enums.OriginType;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.EletronicPointsArchiveMapper;
import pbd.ponto_eletronico.mapper.EmployeesRolesMapper;
import pbd.ponto_eletronico.repository.EletronicPoinstArchiverRepository;
import pbd.ponto_eletronico.request.EletronicPointsArchivePutRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class EletronicPointsArchiveService {
    private final EletronicPoinstArchiverRepository eletronicPoinstArchiverRepository;
    private final EletronicPointsArchiveMapper eletronicPointsArchiveMapper;
    private final EmployeesRolesService employeesRolesService;
    private final EmployeesRolesMapper employeesRolesMapper;
    private final SeqCodeService seqCodeService;

    public List<EletronicPointsArchiveDTO> importArchive(MultipartFile file) {
        Scanner sc = null;
        List<String> lines = new ArrayList<>();

        try {
            sc = new Scanner(file.getInputStream());

            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }

        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return buildObject(lines, file.getOriginalFilename());
    }
    
    private List<EletronicPointsArchiveDTO> buildObject(@NonNull List<String> list, String fileName) {
        List<EletronicPointsArchiveDTO> preVisualizationObjects = new ArrayList<>();
        int lineSize = 0;
        String fileBatchCode = generateFileBatchName(fileName, seqCodeService.getSeqCode());
        for (String string : list) {
            EletronicPointsArchiveDTO eletronicPointsArchiveDTOCurrent= null;
            lineSize = string.length();

            if (lineSize == 24) {
                eletronicPointsArchiveDTOCurrent = buidObejectLineSize24(string, fileBatchCode);
            }

            if (lineSize == 28) {
                eletronicPointsArchiveDTOCurrent = buidObejectLineSize28(string, fileBatchCode);
            }

            if (lineSize == 32) {
                eletronicPointsArchiveDTOCurrent = buidObejectLineSize32(string, fileBatchCode);
            }

            if (lineSize == 36) {
                eletronicPointsArchiveDTOCurrent = buidObejectLineSize36(string, fileBatchCode);
            }

            preVisualizationObjects.add(eletronicPointsArchiveDTOCurrent);

            if (eletronicPointsArchiveDTOCurrent != null) {
                save(eletronicPointsArchiveDTOCurrent);
            }
        }
        return preVisualizationObjects;
    }

    public List<EletronicPointsArchiveDTO> findAll() {
        List<EletronicPointsArchive> eletronicPointsArchivesData = eletronicPoinstArchiverRepository.findAll();

        if (eletronicPointsArchivesData.isEmpty()) {
            throw new BadRequestException("There are no data in the database.");
        }

        return eletronicPointsArchiveMapper.listEletronicPointsArchiveToListEletronicPointsArchiveDTO(eletronicPointsArchivesData);
    }

    public EletronicPointsArchiveDTO findById(Long id) {
        Optional<EletronicPointsArchive> eletronicPointsArchiveData = eletronicPoinstArchiverRepository.findById(id);
        return eletronicPointsArchiveMapper.eletronicPointsArchiveToEletronicPointsArchiveDTO(eletronicPointsArchiveData.orElseThrow(() -> new BadRequestException("Id not found")));
    }

    public List<EletronicPointsArchiveDTO> findByFileBatch(String fileBatch) {
        List<EletronicPointsArchive> eletronicPointsArchivesData = eletronicPoinstArchiverRepository.findByFileBatch(fileBatch);

        if (eletronicPointsArchivesData.isEmpty()) {
            throw new BadRequestException("This batch of files does not exist in the database.");
        }

        return eletronicPointsArchiveMapper.listEletronicPointsArchiveToListEletronicPointsArchiveDTO(eletronicPointsArchivesData);
    }

    public List<EletronicPointsArchiveDTO> findByStatusArchive(EletronicPointArchiveStatus statusArchive) {
        List<EletronicPointsArchive> eletronicPointsArchivesData = eletronicPoinstArchiverRepository.findByStatusArchive(statusArchive);

        if (eletronicPointsArchivesData.isEmpty()) {
            throw new BadRequestException("This status archive does not exist in the database.");
        }

        return eletronicPointsArchiveMapper.listEletronicPointsArchiveToListEletronicPointsArchiveDTO(eletronicPointsArchivesData);
    }

    public EletronicPointsArchive save(EletronicPointsArchiveDTO eletronicPointsArchiveDTO) {
        EmployeesRoles employeesRolesData = employeesRolesMapper.toEmployeesRoles(employeesRolesService.findById(eletronicPointsArchiveDTO.employeesRolesId()));

        if (employeesRolesData == null) {
            throw new BadRequestException("Employee Roles not exists!");
        }
        return eletronicPoinstArchiverRepository.save(eletronicPointsArchiveMapper.eletronicPointsArchiveDTOToEletronicPointsArchive(eletronicPointsArchiveDTO));
    }

    public EletronicPointsArchive replace(EletronicPointsArchivePutRequest eletronicPointsArchivePutRequest) {
        EletronicPointsArchive eletronicPointsArchive = eletronicPoinstArchiverRepository.getReferenceById(eletronicPointsArchivePutRequest.id());
        EmployeesRoles employeesRolesData = employeesRolesMapper.toEmployeesRoles(employeesRolesService.findById(eletronicPointsArchive.getEmployeesRolesId()));

        eletronicPointsArchive.setId(eletronicPointsArchive.getId());
        eletronicPointsArchive.setStatusArchive(EletronicPointArchiveStatus.Validado);
        eletronicPointsArchive.setEmployeesRolesId(eletronicPointsArchive.getEmployeesRolesId());

        return eletronicPoinstArchiverRepository.save(eletronicPointsArchive);
    }

    private LocalDate parseStringDateToLocalDate(String date) {
        return LocalDate.of(
                Integer.parseInt(date.substring(0,4)),
                Integer.parseInt(date.substring(4,6)),
                Integer.parseInt(date.substring(6,8))
        );
    }

    private LocalTime parseStringTimeToLocalTime(String time) {
        return LocalTime.of(
                Integer.parseInt(time.substring(0,2)),
                Integer.parseInt(time.substring(2,3))
        );
    }

    private EletronicPointsArchiveDTO buidObejectLineSize24(String line, String fileBacth) {
        return new EletronicPointsArchiveDTO(
                parseStringDateToLocalDate(line.substring(3, 11)),
                parseStringTimeToLocalTime(line.substring(11,15)),
                parseStringDateToLocalDate(line.substring(15, 23)),
                OriginType.Importado,
                Integer.valueOf(line.substring(23)),
                Long.valueOf(line.substring(0, 3)),
                EletronicPointArchiveStatus.Pendente,
                LocalDateTime.now(),
                fileBacth
        );
    }

    private EletronicPointsArchiveDTO buidObejectLineSize28(String line, String fileBatch) {
        return new EletronicPointsArchiveDTO(
                parseStringDateToLocalDate(line.substring(3, 11)),
                parseStringTimeToLocalTime(line.substring(11,15)),
                parseStringTimeToLocalTime(line.substring(16,20)),
                parseStringDateToLocalDate(line.substring(19, 27)),
                OriginType.Importado,
                Integer.valueOf(line.substring(27)),
                Long.valueOf(line.substring(0, 3)),
                EletronicPointArchiveStatus.Pendente,
                LocalDateTime.now(),
                fileBatch
        );
    }

    private EletronicPointsArchiveDTO buidObejectLineSize32(String line, String fileBatch) {
        return new EletronicPointsArchiveDTO(
                parseStringDateToLocalDate(line.substring(3, 11)),
                parseStringTimeToLocalTime(line.substring(11,15)),
                parseStringTimeToLocalTime(line.substring(15,19)),
                parseStringTimeToLocalTime(line.substring(19,23)),
                parseStringDateToLocalDate(line.substring(23, 31)),
                OriginType.Importado,
                Integer.valueOf(line.substring(31)),
                Long.valueOf(line.substring(0, 3)),
                EletronicPointArchiveStatus.Pendente,
                LocalDateTime.now(),
                fileBatch
        );
    }

    private EletronicPointsArchiveDTO buidObejectLineSize36(String line, String fileBatch) {
        return new EletronicPointsArchiveDTO(
                parseStringDateToLocalDate(line.substring(3, 11)),
                parseStringTimeToLocalTime(line.substring(11,15)),
                parseStringTimeToLocalTime(line.substring(15,19)),
                parseStringTimeToLocalTime(line.substring(19,23)),
                parseStringTimeToLocalTime(line.substring(23,27)),
                parseStringDateToLocalDate(line.substring(27, 35)),
                OriginType.Importado,
                Integer.valueOf(line.substring(35)),
                Long.valueOf(line.substring(0, 3)),
                EletronicPointArchiveStatus.Pendente,
                LocalDateTime.now(),
                fileBatch
        );
    }

    private String generateFileBatchName(String fileName, Long fileBatchCode) {
        return fileBatchCode + "-" + fileName;
    }
}
