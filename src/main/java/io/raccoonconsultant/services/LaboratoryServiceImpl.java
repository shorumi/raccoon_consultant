package io.raccoonconsultant.services;

import io.raccoonconsultant.api.v1.mappers.LaboratoryMapper;
import io.raccoonconsultant.api.v1.models.LaboratoryDTO;
import io.raccoonconsultant.controllers.api.v1.LaboratoryController;
import io.raccoonconsultant.custom.exceptions.ResourceNotFoundException;
import io.raccoonconsultant.entities.Laboratory;
import io.raccoonconsultant.entities.Status;
import io.raccoonconsultant.repositories.LaboratoryRepository;
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
            .map(laboratory -> {
              LaboratoryDTO laboratoryDTO = laboratoryMapper.laboratoryToLaboratoryDTO(laboratory);
              laboratoryDTO.setLaboratoryUrl(getLaboratoryUrl(laboratory.getId()));
              return laboratoryDTO;
            })
            .collect(Collectors.toList());
  }

  @Override
  public LaboratoryDTO createNewLaboratory(LaboratoryDTO laboratoryDto) {
    return saveAndReturnDTO(laboratoryMapper.laboratoryDtoToLaboratory(laboratoryDto));
  }

  @Override
  public LaboratoryDTO putLaboratoryByDTO(Long id, LaboratoryDTO laboratoryDTO) {
    Laboratory laboratory = laboratoryMapper.laboratoryDtoToLaboratory(laboratoryDTO);
    laboratory.setId(id);

    return saveAndReturnDTO(laboratory);
  }

  private LaboratoryDTO saveAndReturnDTO(Laboratory laboratory) {
    Laboratory savedLaboratory = laboratoryRepository.save(laboratory);
    LaboratoryDTO returnedLaboratoryDTO = laboratoryMapper.laboratoryToLaboratoryDTO(savedLaboratory);

    returnedLaboratoryDTO.setLaboratoryUrl(getLaboratoryUrl(savedLaboratory.getId()));

    return returnedLaboratoryDTO;
  }

  @Override
  public LaboratoryDTO patchLaboratoryByDTO(Long id, LaboratoryDTO laboratoryDTO) {
    return laboratoryRepository.findById(id).map(laboratory -> {
      if(laboratoryDTO.getName() != null) {
        laboratory.setName(laboratoryDTO.getName());
      }

      if(laboratoryDTO.getAddress() != null) {
        laboratory.setAddress(laboratoryDTO.getAddress());
      }

      if(laboratoryDTO.getStatus() != null) {
        laboratory.setStatus(laboratoryDTO.getStatus());
      }

      LaboratoryDTO returnLaboratoryDTO = laboratoryMapper.laboratoryToLaboratoryDTO(
              laboratoryRepository.save(laboratory)
      );

      returnLaboratoryDTO.setLaboratoryUrl(getLaboratoryUrl(id));

      return returnLaboratoryDTO;
    }).orElseThrow(() -> new ResourceNotFoundException("Invalid laboratory id: " + id + " or data passed"));
  }

  @Override
  public void deleteLogicallyLaboratoryById(Long id) {
    laboratoryRepository.deleteById(id);
  }

  @Override
  public List<LaboratoryDTO> findAllByStatus(Status status) {
    return laboratoryRepository.findAllByStatus(status)
            .stream()
            .map(laboratory -> {
              LaboratoryDTO laboratoryDTO = laboratoryMapper.laboratoryToLaboratoryDTO(laboratory);
              laboratoryDTO.setLaboratoryUrl(getLaboratoryUrl(laboratory.getId()));
              return laboratoryDTO;
            })
            .collect(Collectors.toList());
  }

  @Override
  public LaboratoryDTO getLaboratoryById(Long id) {
    return laboratoryRepository.findById(id)
            .map(laboratoryMapper::laboratoryToLaboratoryDTO)
            .map(laboratoryDTO -> {
              laboratoryDTO.setLaboratoryUrl(getLaboratoryUrl(id));
              return laboratoryDTO;
            })
            .orElseThrow(() -> new ResourceNotFoundException("Invalid laboratory id: " + id));
  }

  private String getLaboratoryUrl(Long id) {
    return LaboratoryController.BASE_URL + "/" + id;
  }
}
