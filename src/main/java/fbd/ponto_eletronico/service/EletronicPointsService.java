package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EletronicPointsDTO;
import fbd.ponto_eletronico.entity.EletronicPoints;
import fbd.ponto_eletronico.mapper.EletronicPointsMapper;
import fbd.ponto_eletronico.repository.EletronicPointsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EletronicPointsService {

    private final EletronicPointsRepository eletronicPointsRepository;
    private final EletronicPointsMapper eletronicPointsMapper;

    public List<EletronicPointsDTO> listAll(){
        List<EletronicPoints> eletronicPoints = eletronicPointsRepository.findAll();
        log.info("Fileds: {}", eletronicPointsMapper.eletronicPointsDto(eletronicPoints));
        return eletronicPointsMapper.eletronicPointsDto(eletronicPoints);
    }
}
