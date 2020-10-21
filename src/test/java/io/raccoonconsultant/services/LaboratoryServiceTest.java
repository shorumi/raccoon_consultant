package br.com.dasa.labexam.services;

import br.com.dasa.labexam.api.v1.mappers.LaboratoryMapper;
import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;
import br.com.dasa.labexam.entities.Laboratory;
import br.com.dasa.labexam.entities.Status;
import br.com.dasa.labexam.repositories.LaboratoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LaboratoryServiceTest {

  LaboratoryDTO laboratoryDTO = new LaboratoryDTO();
  Laboratory savedLaboratory = new Laboratory();

  private static LaboratoryServiceImpl laboratoryService;

  @Mock
  LaboratoryRepository laboratoryRepository;

  @BeforeEach
  void setUp() throws Exception {
    laboratoryDTO.setName("Bruce Leee");
    laboratoryDTO.setAddress("Chuck Norris street");
    laboratoryDTO.setStatus(Status.ACTIVE);

    savedLaboratory.setName(laboratoryDTO.getName());
    savedLaboratory.setAddress(laboratoryDTO.getAddress());
    savedLaboratory.setStatus(laboratoryDTO.getStatus());
    savedLaboratory.setId(1L);

    laboratoryService = new LaboratoryServiceImpl(LaboratoryMapper.INSTANCE, laboratoryRepository);
  }

  @Test
  @DisplayName("Creates 3 mocked laboratories and then returns a list of Laboratory' DTO")
  public void testGetThreeMockedLaboratories() throws Exception {
    // Given
    List<Laboratory> laboratoriesList = instantiateLabotarories(3, Status.ACTIVE);

    Mockito.when(laboratoryRepository.findAll()).thenReturn(laboratoriesList);

    // When
    List<LaboratoryDTO> laboratoryDTOList = laboratoryService.getAllLaboratories();

    // Then
    assertEquals(3, laboratoryDTOList.size());
  }

  @Test
  @DisplayName("Creates a new Laboratory")
  public void testCreateLaboratory() throws Exception {
    // Given
    Mockito.when(laboratoryRepository.save(any(Laboratory.class))).thenReturn(savedLaboratory);

    // When
    LaboratoryDTO savedLaboratoryDTO = laboratoryService.createNewLaboratory(laboratoryDTO);

    // Then
    assertEquals(laboratoryDTO.getName(), savedLaboratoryDTO.getName());
    assertEquals(laboratoryDTO.getAddress(), savedLaboratoryDTO.getAddress());
    assertEquals(laboratoryDTO.getStatus(), savedLaboratoryDTO.getStatus());
    assertEquals("/api/v1/laboratories/1", savedLaboratoryDTO.getLaboratoryUrl());
  }

  @Test
  @DisplayName("Updates a Laboratory")
  public void testUpdateLaboratory() throws Exception {
    // Given
    Mockito.when(laboratoryRepository.save(any(Laboratory.class))).thenReturn(savedLaboratory);

    // When
    LaboratoryDTO savedDTO = laboratoryService.saveLaboratoryByDTO(1L, laboratoryDTO);

    // Then
    assertEquals(laboratoryDTO.getName(), savedDTO.getName());
    assertEquals(laboratoryDTO.getAddress(), savedDTO.getAddress());
    assertEquals(laboratoryDTO.getStatus(), savedDTO.getStatus());
    assertEquals("/api/v1/laboratories/1", savedDTO.getLaboratoryUrl());
  }

  @Test
  @DisplayName("Deletes logically a laboratory")
  public void testDeleteLogicallyLaboratory() throws Exception {
    // Given
    Long id = 1L;

    // When
    laboratoryRepository.deleteById(id);

    // Then
    Mockito.verify(laboratoryRepository, times(1)).deleteById(anyLong());
  }

  @Test
  @DisplayName("Find all laboratories by status ACTIVE")
  public void testFindAllActiveLaboratories() throws Exception {
    // Given
    List<Laboratory> laboratoriesList = instantiateLabotarories(3, Status.ACTIVE);

    Mockito.when(laboratoryRepository.findAllByStatus(Status.ACTIVE)).thenReturn(laboratoriesList);

    // When
    List<LaboratoryDTO> activeStatusLaboratories = laboratoryService.findAllByStatus(Status.ACTIVE);

    // Then
    assertEquals(3, activeStatusLaboratories.size());

  }

  @Test
  @DisplayName("Find all laboratories by status INACTIVE")
  public void testFindAllInactiveLaboratories() throws Exception {
    // Given
    List<Laboratory> laboratoriesList = instantiateLabotarories(3, Status.INACTIVE);
    Date date = new Date();
    Laboratory laboratory = new Laboratory(
            1L,
            new Timestamp(date.getTime()),
            new Timestamp(date.getTime()),
            "Bruce Lee",
            "Fighters Street",
            Status.ACTIVE,
            true
    );

    Mockito.when(laboratoryRepository.findAllByStatus(Status.INACTIVE)).thenReturn(laboratoriesList);

    // When
    List<LaboratoryDTO> activeStatusLaboratories = laboratoryService.findAllByStatus(Status.INACTIVE);

    // Then
    assertEquals(3, activeStatusLaboratories.size());

  }

  private List<Laboratory> instantiateLabotarories(Integer quantity, Status status) {
    final int TOTAL_OF_LABS = quantity;
    List<Laboratory> laboratoriesList = new ArrayList<Laboratory>();
    Date date = new Date();

    for (int i = 0; i++ < TOTAL_OF_LABS; )
      laboratoriesList.add(
              new Laboratory(
                      1L,
                      new Timestamp(date.getTime()),
                      new Timestamp(date.getTime()),
                      "Bruce Lee",
                      "Fighters Street",
                      status,
                      true
              )
      );

    return laboratoriesList;
  }
}

