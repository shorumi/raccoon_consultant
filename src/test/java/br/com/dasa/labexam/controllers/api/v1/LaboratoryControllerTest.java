package br.com.dasa.labexam.controllers.api.v1;

import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;
import br.com.dasa.labexam.entities.Status;
import br.com.dasa.labexam.services.LaboratoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LaboratoryControllerTest {

  @Mock
  LaboratoryService laboratoryService;

  @InjectMocks
  LaboratoryController laboratoryController;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(laboratoryController).build();
  }


  @Test
  @DisplayName("Make a REST GET request to the Laboratory Controller index and returns a JSON with laboratories")
  public void testIndex() throws Exception {
    // Given
    final int TOTAL_OF_LABS = 3;
    List<LaboratoryDTO> laboratoriesList = new ArrayList<LaboratoryDTO>();

    for (int i = 0; i++ < TOTAL_OF_LABS; )
      laboratoriesList.add(
              new LaboratoryDTO(
                      (long) i, "Chuck Norris Lab: " + i, "Chuck Norris Street" + i, Status.ACTIVE
              )
      );

    Mockito.when(laboratoryService.getAllLaboratories()).thenReturn(laboratoriesList);

    String expected = "" +
            "{" +
            "laboratories:" +
            "[" +
            "{" +
            "id:1," +
            "name:\"Chuck Norris Lab: 1\"," +
            "address: \"Chuck Norris Street1\"," +
            "status: \"ACTIVE\"," +
            "createdAt: null," +
            "updatedAt: null" +
            "}," +
            "{" +
            "id: 2," +
            "name: \"Chuck Norris Lab: 2\"," +
            "address: \"Chuck Norris Street2\"," +
            "status: \"ACTIVE\"," +
            "createdAt: null," +
            "updatedAt: null" +
            "}," +
            "{" +
            "id: 3," +
            "name: \"Chuck Norris Lab: 3\"," +
            "address: \"Chuck Norris Street3\"," +
            "status: \"ACTIVE\"," +
            "createdAt: null," +
            "updatedAt: null" +
            "}" +
            "]" +
            "}";

    // When & Then
    MvcResult mvcResult = mockMvc.perform(get("/api/v1/laboratories/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.laboratories", hasSize(3)))
            .andReturn();

    String actual = mvcResult.getResponse().getContentAsString();

    JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
  }

}