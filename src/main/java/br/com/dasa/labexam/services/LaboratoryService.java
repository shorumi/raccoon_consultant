package br.com.dasa.labexam.services;

import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;

import java.util.List;

public interface LaboratoryService {
  List<LaboratoryDTO> getAllLaboratories();

}
