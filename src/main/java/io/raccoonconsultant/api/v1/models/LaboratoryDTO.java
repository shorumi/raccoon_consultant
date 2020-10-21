package io.raccoonconsultant.api.v1.models;

import io.raccoonconsultant.entities.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class LaboratoryDTO {
  Long id;

  @Size(max = 100)
  @NotEmpty(message = "Name is required")
  String name;

  @Column(name = "address")
  @Size(max = 255)
  @NotEmpty(message = "Address is required")
  String address;

  @NotNull(message = "Status should not be null")
  Status status = Status.ACTIVE;

  @JsonProperty("created_at")
  Timestamp createdAt;

  @JsonProperty("updated_at")
  Timestamp updatedAt;

  @JsonProperty("laboratory_url")
  String laboratoryUrl;

  @Column(name = "deleted")
  Boolean deleted = false;

  public LaboratoryDTO(Long id, String name, String address, Status status) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.status = status;
  }
}

