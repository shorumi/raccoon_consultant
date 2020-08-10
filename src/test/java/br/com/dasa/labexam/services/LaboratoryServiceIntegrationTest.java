package br.com.dasa.labexam.services;

import br.com.dasa.labexam.api.v1.mappers.LaboratoryMapper;
import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;
import br.com.dasa.labexam.entities.Laboratory;
import br.com.dasa.labexam.helpers.factories.LaboratoryMother;
import br.com.dasa.labexam.repositories.LaboratoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LaboratoryServiceIntegrationTest {

  @Autowired
  LaboratoryRepository laboratoryRepository;

  LaboratoryService laboratoryService;

  // Laboratory Factory
  Laboratory laboratory = LaboratoryMother.complete().build();

  @BeforeEach
  @DisplayName("Laboratory Integration TEST")
  public void setUp() throws Exception {
    laboratoryService = new LaboratoryServiceImpl(LaboratoryMapper.INSTANCE, laboratoryRepository);
  }

  @Test
  @DisplayName("Patch Address property")
  public void testIndex() throws Exception {
    // Given
    laboratoryRepository.save(laboratory);

    String expectedAddress = "Updated Addressssss!!!!";
    long id = getLaboratoryIdValue();

    LaboratoryDTO laboratoryDTO = new LaboratoryDTO();
    laboratoryDTO.setAddress(expectedAddress);

    // When
    Laboratory originalLaboratory = laboratoryRepository.getOne(id);
    String actualAddress = originalLaboratory.getAddress();

    laboratoryService.patchLaboratoryByDTO(id, laboratoryDTO);

    Laboratory updatedLaboratory = laboratoryRepository.findById(id).get();

    // Then
    assertNotNull(originalLaboratory);
    assertNotNull(updatedLaboratory);
    assertEquals(expectedAddress, updatedLaboratory.getAddress());
    assertNotEquals(actualAddress, updatedLaboratory.getAddress());
  }

  private Long getLaboratoryIdValue() {
    List<Laboratory> laboratories = laboratoryRepository.findAll();

    return laboratories.get(0).getId();
  }
}
