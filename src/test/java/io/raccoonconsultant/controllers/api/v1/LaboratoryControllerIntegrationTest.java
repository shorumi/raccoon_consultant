//package io.raccoonconsultant.controllers.api.v1;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.security.test.context.support.WithMockUser;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest
//class LaboratoryControllerIntegrationTest extends BaseIntegrationTest {
//
//  @WithMockUser("spring")
//  @Test
//  void findLaboratories() throws Exception {
//    mockMvc.perform(get(LaboratoryController.BASE_URL))
//            .andExpect(status().isOk());
//  }
//
//  @Test
//  void findLaboratoriesWithHttpBasicAuth() throws Exception {
//    mockMvc.perform(get(LaboratoryController.BASE_URL).with(httpBasic("kami", "sama")))
//            .andExpect(status().isOk());
//  }
//
//
//}
