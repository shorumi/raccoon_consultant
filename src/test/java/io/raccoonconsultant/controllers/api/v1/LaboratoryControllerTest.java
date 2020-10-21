package io.raccoonconsultant.controllers.api.v1;

import io.raccoonconsultant.api.v1.models.LaboratoryDTO;
import io.raccoonconsultant.entities.Status;
import io.raccoonconsultant.helpers.AbstractRestHelperController;
import io.raccoonconsultant.services.LaboratoryService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    laboratoryDTO.setId((long) 1);
    laboratoryDTO.setName("Charles Bronson Lab");
    laboratoryDTO.setAddress("Bruce Lee street");
    laboratoryDTO.setLaboratoryUrl(LaboratoryController.BASE_URL + "/1");
    laboratoryDTO.setStatus(Status.ACTIVE);

    returnedLaboratoryDTO.setName(laboratoryDTO.getName());
    returnedLaboratoryDTO.setAddress(laboratoryDTO.getAddress());
    returnedLaboratoryDTO.setStatus(laboratoryDTO.getStatus());
    returnedLaboratoryDTO.setLaboratoryUrl("/api/v1/laboratories/1");
  }


  @Test
  @DisplayName("REST GET request to the Laboratory Controller index and returns a JSON with laboratories")
  public void testIndex() throws Exception {
    // Given
    List<LaboratoryDTO> laboratoriesList = instantiateLabotarories(3, Status.ACTIVE);

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
            "created_at: null," +
            "updated_at: null" +
            "}," +
            "{" +
            "id: 2," +
            "name: \"Chuck Norris Lab: 2\"," +
            "address: \"Chuck Norris Street2\"," +
            "status: \"ACTIVE\"," +
            "created_at: null," +
            "updated_at: null" +
            "}," +
            "{" +
            "id: 3," +
            "name: \"Chuck Norris Lab: 3\"," +
            "address: \"Chuck Norris Street3\"," +
            "status: \"ACTIVE\"," +
            "created_at: null," +
            "updated_at: null" +
            "}" +
            "]" +
            "}";

    // When & Then
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .get(LaboratoryController.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.laboratories", hasSize(3)))
            .andReturn();

    String actual = mvcResult.getResponse().getContentAsString();

    JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
  }

  @Test
  @DisplayName("REST POST request to the Laboratory Controller create action and creates a Lab resource")
  public void testCreate() throws Exception {
    // Given
    Mockito.when(laboratoryService.createNewLaboratory(laboratoryDTO)).thenReturn(returnedLaboratoryDTO);

    // When & Then
    mockMvc.perform(MockMvcRequestBuilders
            .post(LaboratoryController.BASE_URL)
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
  @DisplayName("REST PUT request to the Laboratory Controller update action and updates a Lab resource")
  public void testUpdate() throws Exception {
    // Given
    Mockito.when(
            laboratoryService.putLaboratoryByDTO(
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
  @DisplayName("REST PATCH request to the Laboratory Controller patch action and updates a Lab resource, partially")
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

  @Test
  @DisplayName("REST DELETE request to the Laboratory Controller, delete a resource logically")
  public void testLogicallyDelete() throws Exception {
    // When && Then
    mockMvc.perform(MockMvcRequestBuilders.delete(LaboratoryController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    Mockito.verify(laboratoryService).deleteLogicallyLaboratoryById(anyLong());
  }

  @Test
  @DisplayName("REST GET request filtering by ACTIVE status parameter")
  public void testFindByStatusParam() throws Exception {
    // Given
    List<LaboratoryDTO> laboratoriesDTOList = instantiateLabotarories(3, Status.ACTIVE);

    Mockito.when(laboratoryService.findAllByStatus(Status.ACTIVE)).thenReturn(laboratoriesDTOList);

    String expected = "" +
            "{" +
            "laboratories:" +
            "[" +
            "{" +
            "id:1," +
            "name:\"Chuck Norris Lab: 1\"," +
            "address: \"Chuck Norris Street1\"," +
            "status: \"ACTIVE\"," +
            "created_at: null," +
            "updated_at: null" +
            "}," +
            "{" +
            "id: 2," +
            "name: \"Chuck Norris Lab: 2\"," +
            "address: \"Chuck Norris Street2\"," +
            "status: \"ACTIVE\"," +
            "created_at: null," +
            "updated_at: null" +
            "}," +
            "{" +
            "id: 3," +
            "name: \"Chuck Norris Lab: 3\"," +
            "address: \"Chuck Norris Street3\"," +
            "status: \"ACTIVE\"," +
            "created_at: null," +
            "updated_at: null" +
            "}" +
            "]" +
            "}";

    // When & Then
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .get(LaboratoryController.BASE_URL + "/findByStatus")
            .param("status", "ACTIVE")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.laboratories", hasSize(3)))
            .andReturn();

    String actual = mvcResult.getResponse().getContentAsString();

    JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);

  }

  @Test
  @DisplayName("REST GET by ID request to the Laboratory Controller  and returns a JSON with laboratory")
  public void testGetLaboratoryById() throws Exception {
    // Given

    String expected = "" +
            "{" +
            "id: 1," +
            "name:\"Charles Bronson Lab\"," +
            "address: \"Bruce Lee street\"," +
            "status: \"ACTIVE\"," +
            "created_at: null," +
            "updated_at: null," +
            "laboratory_url: \"/api/v1/laboratories/1\"" +
            "}";

    Mockito.when(laboratoryService.getLaboratoryById(anyLong())).thenReturn(laboratoryDTO);

    // When & Then
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .get(LaboratoryController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Charles Bronson Lab")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.address", equalTo("Bruce Lee street")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status", equalTo("ACTIVE")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.laboratory_url", equalTo("/api/v1/laboratories/1")))
            .andReturn();

    String actual = mvcResult.getResponse().getContentAsString();

    JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
  }

  private List<LaboratoryDTO> instantiateLabotarories(Integer quantity, Status status) {
    final int TOTAL_OF_LABS = quantity;

    List<LaboratoryDTO> laboratoriesList = new ArrayList<LaboratoryDTO>();

    for (int i = 0; i++ < TOTAL_OF_LABS; )
      laboratoriesList.add(
              new LaboratoryDTO((long) i, "Chuck Norris Lab: " + i, "Chuck Norris Street" + i, status)
      );

    return laboratoriesList;
  }

}