package pbd.ponto_eletronico.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbd.ponto_eletronico.dto.SectorsDTO;
import pbd.ponto_eletronico.entity.Company;
import pbd.ponto_eletronico.entity.Sectors;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.CompanyMapper;
import pbd.ponto_eletronico.mapper.RoleMapper;
import pbd.ponto_eletronico.mapper.SectorsMapper;
import pbd.ponto_eletronico.repository.CompanyRepository;
import pbd.ponto_eletronico.repository.SectorsRepository;
import pbd.ponto_eletronico.request.SectorsPostRequest;
import pbd.ponto_eletronico.request.SectorsPutRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectorsService {
    private final SectorsRepository sectorsRepository;
    private final SectorsMapper sectorsMapper;

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    public List<SectorsDTO> findAll() {
        List<Sectors> sectorsData = sectorsRepository.findAll();
        return sectorsMapper.listSectorsToListSectorsDTO(sectorsData);
    }

    public SectorsDTO findById(Long id) {
        Sectors sectorData = sectorsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sector not found with this ID!"));
        return sectorsMapper.sectorsToSectorsDTO(sectorData);
    }

    public List<SectorsDTO> findByName(String name) {
        List<Sectors> sectorsData = sectorsRepository.findByName(name);
        return sectorsMapper.listSectorsToListSectorsDTO(sectorsData);
    }
    public SectorsDTO findByCompanyId(Long id) {
        Sectors sectorsData = sectorsRepository.findByCompany_Id(id);
        return sectorsMapper.sectorsToSectorsDTO(sectorsData);
    }

    public SectorsDTO findByNameAndCompanyId(String name, Long id) {
        Sectors sectorsData = sectorsRepository.findByNameAndCompany_Id(name, id);
        return sectorsMapper.sectorsToSectorsDTO(sectorsData);
    }

    @Transactional
    public Sectors save(SectorsPostRequest sectorsPostRequest) {
        if (sectorsPostRequest.sigla().length() > 5)
            throw new RuntimeException("Sigla length mustn't be greater than 5");

        Company companyData = companyRepository.findById(sectorsPostRequest.companyId()).orElseThrow();
        Sectors sectorsData = sectorsMapper.sectorsPostRequestToSectors(sectorsPostRequest);
        sectorsData.setCompany(companyData);
        return sectorsRepository.save(sectorsData);
    }

    public SectorsDTO replace(Long id, SectorsPutRequest sectorsPutRequest) {
        Company companyData = companyRepository.findById(sectorsPutRequest.companyId()).orElseThrow();
        Sectors sectorsData = sectorsRepository.getReferenceById(id);
        Sectors sectorsReplace = sectorsMapper.sectorsPutRequestToSectors(sectorsPutRequest);
        sectorsReplace.setId(sectorsData.getId());
        sectorsReplace.setCompany(companyData);
        return sectorsMapper.sectorsToSectorsDTO(sectorsRepository.save(sectorsReplace));
    };

    public void delete(Long id){
        Sectors sectorsData = sectorsRepository.getReferenceById(id);
        sectorsRepository.delete(sectorsData);
    }
}
