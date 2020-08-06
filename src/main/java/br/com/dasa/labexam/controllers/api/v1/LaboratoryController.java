package br.com.dasa.labexam.controllers.api.v1;

import br.com.dasa.labexam.api.v1.models.LaboratoryListDTO;
import br.com.dasa.labexam.services.LaboratoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/laboratories")
public class LaboratoryController {

  private final LaboratoryService laboratoryService;

  public LaboratoryController(@Qualifier("laboratoryServiceImpl") LaboratoryService laboratoryService) {
    this.laboratoryService = laboratoryService;
  }

  @GetMapping
  public ResponseEntity<LaboratoryListDTO> index() {
    return new ResponseEntity<LaboratoryListDTO>(
            new LaboratoryListDTO(laboratoryService.getAllLaboratories()),HttpStatus.OK
    );
  }
}
