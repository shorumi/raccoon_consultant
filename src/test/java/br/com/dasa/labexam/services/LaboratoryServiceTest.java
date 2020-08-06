package br.com.dasa.labexam.services;

import br.com.dasa.labexam.api.v1.mappers.LaboratoryMapper;
import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;
import br.com.dasa.labexam.entities.Laboratory;
import br.com.dasa.labexam.repositories.LaboratoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LaboratoryServiceTest {

  private static LaboratoryServiceImpl laboratoryService;

  @Mock
  LaboratoryRepository laboratoryRepository;

  @BeforeEach
  void setUp() throws Exception {
    laboratoryService = new LaboratoryServiceImpl(LaboratoryMapper.INSTANCE, laboratoryRepository);
  }

  @Test
  @DisplayName("Creates 3 mocked laboratories and then returns a list of Laboratory' DTO")
  public void testGetThreeMockedLaboratories() throws Exception {
    // Given
    final int TOTAL_OF_LABS = 3;
    List<Laboratory> laboratoriesList = new ArrayList<Laboratory>();

    for (int i = 0; i++ < TOTAL_OF_LABS;)
      laboratoriesList.add(new Laboratory());

    Mockito.when(laboratoryRepository.findAll()).thenReturn(laboratoriesList);

    // When
    List<LaboratoryDTO> laboratoryDTOListLaboratories = laboratoryService.getAllLaboratories();

    // Then
    assertEquals(3, laboratoryDTOListLaboratories.size());
  }

}