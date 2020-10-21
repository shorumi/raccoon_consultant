package io.raccoonconsultant.api.v1.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LaboratoryListDTO {
  List<LaboratoryDTO> laboratories;
}
