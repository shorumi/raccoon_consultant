package br.com.dasa.labexam.api.v1.mappers;

import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;
import br.com.dasa.labexam.entities.Laboratory;
import br.com.dasa.labexam.entities.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LaboratoryMapperTest {

  LaboratoryMapper laboratoryMapper = LaboratoryMapper.INSTANCE;

  @Test
  @DisplayName("Validates that a laboratory entity could be mapped to a DTO and it's properties are accessible")
  public void laboratoryToLaboratoryDTO() throws Exception {
    // Given
    Laboratory laboratory = new Laboratory();
    laboratory.setName("Chuck Norris");
    laboratory.setAddress("38, Charles bronson street");
    laboratory.setStatus(Status.ACTIVE);
    laboratory.setId(1L);
    laboratory.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
    laboratory.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

    // When
    LaboratoryDTO laboratoryDTO = laboratoryMapper.laboratoryToLaboratoryDTO(laboratory);

    // Then
    assertEquals(1L, laboratoryDTO.getId());
    assertEquals("Chuck Norris", laboratoryDTO.getName());
    assertEquals("38, Charles bronson street", laboratoryDTO.getAddress());
    assertEquals(Status.ACTIVE, laboratoryDTO.getStatus());
    assertNotNull(laboratoryDTO.getCreatedAt());
    assertNotNull(laboratoryDTO.getUpdatedAt());

  }

}