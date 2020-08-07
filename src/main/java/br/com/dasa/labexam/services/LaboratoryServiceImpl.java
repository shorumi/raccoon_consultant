package br.com.dasa.labexam.services;

import br.com.dasa.labexam.api.v1.mappers.LaboratoryMapper;
import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;
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
    Laboratory laboratory = laboratoryMapper.laboratoryDtoToLaboratory(laboratoryDto);
    Laboratory savedLaboratory = laboratoryRepository.save(laboratory);
    LaboratoryDTO returnedLaboratoryDto = laboratoryMapper.laboratoryToLaboratoryDTO(savedLaboratory);

    returnedLaboratoryDto.setLaboratoryUrl("/api/v1/laboratory/" + savedLaboratory.getId());

    return returnedLaboratoryDto;
  }


}
