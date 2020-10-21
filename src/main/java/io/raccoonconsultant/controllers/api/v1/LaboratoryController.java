package io.raccoonconsultant.controllers.api.v1;

import io.raccoonconsultant.api.v1.models.LaboratoryDTO;
import io.raccoonconsultant.api.v1.models.LaboratoryListDTO;
import io.raccoonconsultant.entities.Status;
import io.raccoonconsultant.services.LaboratoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(LaboratoryController.BASE_URL)
public class LaboratoryController {

  public static final String BASE_URL = "/api/v1/laboratories";

  public static final Logger logger = LoggerFactory.getLogger(LaboratoryController.class);

  private final LaboratoryService laboratoryService;

  public LaboratoryController(LaboratoryService laboratoryService) {
    this.laboratoryService = laboratoryService;
  }

  @GetMapping
  public ResponseEntity<LaboratoryListDTO> index() {
    return new ResponseEntity<>(
            new LaboratoryListDTO(laboratoryService.getAllLaboratories()), HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<LaboratoryDTO> create(@Valid @RequestBody LaboratoryDTO laboratoryDTO) {
    return new ResponseEntity<>(laboratoryService.createNewLaboratory(laboratoryDTO), HttpStatus.CREATED);
  }

  @PutMapping({"/{id}"})
  public ResponseEntity<LaboratoryDTO> update(@PathVariable Long id, @RequestBody LaboratoryDTO laboratoryDTO) {
    logger.info("Updating customer with id {}", id);

    return new ResponseEntity<>(laboratoryService.putLaboratoryByDTO(id, laboratoryDTO), HttpStatus.OK);
  }

  @PatchMapping({"/{id}"})
  public ResponseEntity<LaboratoryDTO> patch(@PathVariable Long id, @RequestBody LaboratoryDTO laboratoryDTO) {
    logger.info("Patching customer with id {}", id);

    return new ResponseEntity<>(laboratoryService.patchLaboratoryByDTO(id, laboratoryDTO), HttpStatus.OK);
  }

  @DeleteMapping({"/{id}"})
  public ResponseEntity<Void> delete(@PathVariable Long id){

    laboratoryService.deleteLogicallyLaboratoryById(id);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @RequestMapping("findByStatus")
  public ResponseEntity<LaboratoryListDTO> getAllByStatus(@RequestParam(value = "status") Status status) {
    return new ResponseEntity<>(
            new LaboratoryListDTO(laboratoryService.findAllByStatus(status)), HttpStatus.OK
    );
  }

  @RequestMapping({"/{id}"})
  public ResponseEntity<LaboratoryDTO> getLaboratoryById(@PathVariable Long id) {
    return new ResponseEntity<>(laboratoryService.getLaboratoryById(id), HttpStatus.OK);
  }
}
