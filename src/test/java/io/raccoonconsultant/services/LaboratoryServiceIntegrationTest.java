package io.raccoonconsultant.services;

import io.raccoonconsultant.api.v1.mappers.LaboratoryMapper;
import io.raccoonconsultant.api.v1.models.LaboratoryDTO;
import io.raccoonconsultant.entities.Laboratory;
import io.raccoonconsultant.entities.Status;
import io.raccoonconsultant.helpers.factories.LaboratoryMother;
import io.raccoonconsultant.repositories.LaboratoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
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
  public void testPatch() throws Exception {
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

  @Test
  @DisplayName("Find all laboratories filtered by Active/Inactive status")
  public void testGetAllLabsByActiveInactiveStatus() throws Exception {
    // Given
    Laboratory inactiveLaboratory = LaboratoryMother.complete().status(Status.INACTIVE).build();
    Laboratory activeLaboratory = LaboratoryMother.complete().status(Status.ACTIVE).build();
    List<Laboratory> laboratoryList = Arrays.asList(inactiveLaboratory, activeLaboratory);

    laboratoryRepository.saveAll(laboratoryList);

    // When
    List<LaboratoryDTO> activeLabsList = laboratoryService.findAllByStatus(Status.ACTIVE);
    List<LaboratoryDTO> inactiveLabsList = laboratoryService.findAllByStatus(Status.INACTIVE);
    List<LaboratoryDTO> allLabsList = laboratoryService.getAllLaboratories();

    // Then
    assertNotNull(activeLabsList);
    assertEquals(1, activeLabsList.size());
    assertEquals(1, inactiveLabsList.size());
    assertEquals(2, allLabsList.size());
  }

  private Long getLaboratoryIdValue() {
    List<Laboratory> laboratories = laboratoryRepository.findAll();

    return laboratories.get(0).getId();
  }
}
