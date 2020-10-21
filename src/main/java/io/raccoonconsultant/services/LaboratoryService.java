package br.com.dasa.labexam.services;

import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;
import br.com.dasa.labexam.entities.Status;

import java.util.List;

public interface LaboratoryService {
  List<LaboratoryDTO> getAllLaboratories();
  LaboratoryDTO createNewLaboratory(LaboratoryDTO laboratoryDto);
  LaboratoryDTO saveLaboratoryByDTO(Long id, LaboratoryDTO laboratoryDTO);
  LaboratoryDTO patchLaboratoryByDTO(Long id, LaboratoryDTO laboratoryDTO);
  void deleteLogicallyLaboratoryById(Long id);
  List<LaboratoryDTO> findAllByStatus(Status status);
}
