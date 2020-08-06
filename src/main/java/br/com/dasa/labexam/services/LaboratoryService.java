package br.com.dasa.labexam.services;

import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;
import br.com.dasa.labexam.entities.Laboratory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LaboratoryService {
  public List<LaboratoryDTO> getAllLaboratories();

}
