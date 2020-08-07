package br.com.dasa.labexam.services;

import br.com.dasa.labexam.api.v1.mappers.LaboratoryMapper;
import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;
import br.com.dasa.labexam.controllers.api.v1.LaboratoryController;
import br.com.dasa.labexam.entities.Laboratory;
import br.com.dasa.labexam.repositories.LaboratoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

  private final LaboratoryMapper laboratoryMapper;
  private final LaboratoryRepository laboratoryRepository;

  public LaboratoryServiceImpl(LaboratoryMapper laboratoryMapper, LaboratoryRepository laboratoryRepository) {
    this.laboratoryMapper = laboratoryMapper;
    this.laboratoryRepository = laboratoryRepository;
  }

  @Override
  public List<LaboratoryDTO> getAllLaboratories() {
    return laboratoryRepository.findAll()
            .stream()
            .map(laboratoryMapper::laboratoryToLaboratoryDTO)
            .collect(Collectors.toList());
  }

  @Override
  public LaboratoryDTO createNewLaboratory(LaboratoryDTO laboratoryDto) {
    return saveAndReturnDTO(laboratoryMapper.laboratoryDtoToLaboratory(laboratoryDto));
  }

  @Override
  public LaboratoryDTO saveLaboratoryByDTO(Long id, LaboratoryDTO laboratoryDTO) {
    Laboratory laboratory = laboratoryMapper.laboratoryDtoToLaboratory(laboratoryDTO);
    laboratory.setId(id);

    return saveAndReturnDTO(laboratory);
  }

  private LaboratoryDTO saveAndReturnDTO(Laboratory laboratory) {
    Laboratory savedLaboratory = laboratoryRepository.save(laboratory);
    LaboratoryDTO returnedLaboratoryDTO = laboratoryMapper.laboratoryToLaboratoryDTO(savedLaboratory);

    returnedLaboratoryDTO.setLaboratoryUrl(getCustomerUrl(savedLaboratory.getId()));

    return returnedLaboratoryDTO;
  }

  private String getCustomerUrl(Long id) {
    return LaboratoryController.BASE_URL + "/" + id;
  }
}
