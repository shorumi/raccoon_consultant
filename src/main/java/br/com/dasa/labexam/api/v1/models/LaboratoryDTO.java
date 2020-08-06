package br.com.dasa.labexam.api.v1.models;


import br.com.dasa.labexam.entities.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class LaboratoryDTO {
  Long id;
  String name;
  String address;
  Status status;
  Timestamp createdAt;
  Timestamp updatedAt;

  public LaboratoryDTO(Long id, String name, String address, Status status) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.status = status;
  }
}

