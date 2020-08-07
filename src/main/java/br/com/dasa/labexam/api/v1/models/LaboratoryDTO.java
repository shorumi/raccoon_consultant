package br.com.dasa.labexam.api.v1.models;


import br.com.dasa.labexam.entities.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class LaboratoryDTO {
  Long id;

  @Size(max = 100)
  @NotBlank(message = "Name is required")
  String name;

  @Column(name = "address")
  @Size(max = 255)
  @NotBlank(message = "Address is required")
  String address;

  @NotNull(message = "Status should not be null")
  Status status = Status.ACTIVE;

  Timestamp createdAt;
  Timestamp updatedAt;

  @JsonProperty("laboratory_url")
  String laboratoryUrl;

  public LaboratoryDTO(Long id, String name, String address, Status status) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.status = status;
  }
}

