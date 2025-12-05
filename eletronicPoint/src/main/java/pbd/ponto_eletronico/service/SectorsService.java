package pbd.ponto_eletronico.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbd.ponto_eletronico.dto.RoleDTO;
import pbd.ponto_eletronico.dto.SectorsDTO;
import pbd.ponto_eletronico.entity.Company;
import pbd.ponto_eletronico.entity.Sectors;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.CompanyMapper;
import pbd.ponto_eletronico.mapper.RoleMapper;
import pbd.ponto_eletronico.mapper.SectorsMapper;
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

//    private final RoleService roleService;
    private final RoleMapper roleMapper;

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public List<SectorsDTO> findAll() {
        List<Sectors> sectorsData = sectorsRepository.findAll();

        if (sectorsData.isEmpty()) {
            throw  new BadRequestException("No Sectores in DataBase.");
        }

        return sectorsMapper.listSectorsToListSectorsDTO(sectorsData);
    }

    public SectorsDTO findById(Long id) {
        Optional<Sectors> sectorData = sectorsRepository.findById(id);
        return sectorsMapper.sectorsToSectorsDTO(sectorData
                .orElseThrow(() -> new BadRequestException("Id not Found")));
    }

    public List<SectorsDTO> findByName(String name) {
        List<Sectors> sectorsData = sectorsRepository.findByName(name);
        return sectorsMapper.listSectorsToListSectorsDTO(sectorsData);
    }

//    public List<RoleDTO> findAllRolesInSector(String name) {
//        return roleService.listAll().stream().filter(roleDTO -> roleDTO.sectors().name().equals(name)).toList();
//    }

    @Transactional
    public Sectors save(SectorsPostRequest sectorsPostRequest) {
        Company companyData = companyMapper.toCompany(companyService.findById(sectorsPostRequest.companyId()));
        Sectors sectorsData = sectorsMapper.sectorsPostRequestToSectors(sectorsPostRequest);
        sectorsData.setCompany(companyData);
        return sectorsRepository.save(sectorsData);
    }

    public SectorsDTO replace(Long id, SectorsPutRequest sectorsPutRequest) {
        Company companyData = companyMapper.toCompany(companyService.findById(sectorsPutRequest.companyId()));
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
