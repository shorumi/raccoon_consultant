package br.com.dasa.labexam.controllers.api.v1;

import br.com.dasa.labexam.api.v1.models.LaboratoryDTO;
import br.com.dasa.labexam.entities.Status;
import br.com.dasa.labexam.helpers.AbstractRestHelperController;
import br.com.dasa.labexam.services.LaboratoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LaboratoryControllerTest extends AbstractRestHelperController {

  LaboratoryDTO laboratoryDTO = new LaboratoryDTO();
  LaboratoryDTO returnedLaboratoryDTO = new LaboratoryDTO();

  @Mock
  LaboratoryService laboratoryService;

  @InjectMocks
  LaboratoryController laboratoryController;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(laboratoryController).build();

    laboratoryDTO.setName("Charles Bronson Lab");
    laboratoryDTO.setAddress("Bruce Lee street");
    laboratoryDTO.setStatus(Status.ACTIVE);

    returnedLaboratoryDTO.setName(laboratoryDTO.getName());
    returnedLaboratoryDTO.setAddress(laboratoryDTO.getAddress());
    returnedLaboratoryDTO.setStatus(laboratoryDTO.getStatus());
    returnedLaboratoryDTO.setLaboratoryUrl("/api/v1/laboratories/1");
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
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/laboratories/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.laboratories", hasSize(3)))
            .andReturn();

    String actual = mvcResult.getResponse().getContentAsString();

    JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
  }

  @Test
  @DisplayName("Makes a REST POST request to the Laboratory Controller create action and creates a Lab resource")
  public void testCreate() throws Exception {
    // Given
    Mockito.when(laboratoryService.createNewLaboratory(laboratoryDTO)).thenReturn(returnedLaboratoryDTO);

    // When & Then
    mockMvc.perform(MockMvcRequestBuilders
            .post("/api/v1/laboratories")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(asJsonString(laboratoryDTO)))
            .andExpect(status().isCreated())
    .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Charles Bronson Lab")))
    .andExpect(MockMvcResultMatchers.jsonPath("$.address", equalTo("Bruce Lee street")))
    .andExpect(MockMvcResultMatchers.jsonPath("$.status", equalTo("ACTIVE")))
    .andExpect(MockMvcResultMatchers.jsonPath("$.laboratory_url", equalTo("/api/v1/laboratories/1")));
  }

  @Test
  @DisplayName("Makes a REST PUT request to the Laboratory Controller update action and updates a Lab resource")
  public void testUpdate() throws Exception {
    // Given
    Mockito.when(
            laboratoryService.saveLaboratoryByDTO(
                    anyLong(), any(LaboratoryDTO.class))
    ).thenReturn(returnedLaboratoryDTO);

    // When & Then
    mockMvc.perform(MockMvcRequestBuilders
            .put(LaboratoryController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(laboratoryDTO)))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Charles Bronson Lab")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.address", equalTo("Bruce Lee street")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status", equalTo("ACTIVE")))
            .andExpect(
                    MockMvcResultMatchers.jsonPath(
                            "$.laboratory_url", equalTo(LaboratoryController.BASE_URL + "/1")
                    )
            );
  }

  @Test
  @DisplayName(
          "Makes a REST PATCH request to the Laboratory Controller patch action and updates a Lab resource, partially"
  )
  public void testPatch() throws Exception {
    // Given

    Mockito.when(
            laboratoryService.patchLaboratoryByDTO(
                    anyLong(), any(LaboratoryDTO.class))
    ).thenReturn(returnedLaboratoryDTO);

    laboratoryDTO.setName("Michael Jackson from outskirt");
    laboratoryDTO.setStatus(Status.INACTIVE);
    returnedLaboratoryDTO.setName(laboratoryDTO.getName());
    returnedLaboratoryDTO.setStatus(laboratoryDTO.getStatus());


    // When & Then
    mockMvc.perform(MockMvcRequestBuilders
    .patch(LaboratoryController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(laboratoryDTO)))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath(
                    "$.name", equalTo("Michael Jackson from outskirt"))
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.address", equalTo("Bruce Lee street")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status", equalTo("INACTIVE")))
            .andExpect(
                    MockMvcResultMatchers.jsonPath(
                            "$.laboratory_url", equalTo(LaboratoryController.BASE_URL + "/1")
                    )
            );
  }
}