package br.com.dasa.labexam.api.v1.models;


import br.com.dasa.labexam.entities.Status;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class LaboratoryDTO {
  Long id;
  String name;
  String address;
  Status status;
  Timestamp createdAt;
  Timestamp updatedAt;
}
