package io.raccoonconsultant.services;

import io.raccoonconsultant.api.v1.models.LaboratoryDTO;
import io.raccoonconsultant.entities.Status;

import java.util.List;

public interface LaboratoryService {
  List<LaboratoryDTO> getAllLaboratories();
  LaboratoryDTO createNewLaboratory(LaboratoryDTO laboratoryDto);
  LaboratoryDTO putLaboratoryByDTO(Long id, LaboratoryDTO laboratoryDTO);
  LaboratoryDTO patchLaboratoryByDTO(Long id, LaboratoryDTO laboratoryDTO);
  void deleteLogicallyLaboratoryById(Long id);
  List<LaboratoryDTO> findAllByStatus(Status status);
  LaboratoryDTO getLaboratoryById(Long id);
}

