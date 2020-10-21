package io.raccoonconsultant.controllers.api.v1;

import io.raccoonconsultant.services.LaboratoryService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public abstract class BaseIntegrationTest {

  @Autowired
  WebApplicationContext wac;

  @MockBean
  LaboratoryService laboratoryService;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders
            .webAppContextSetup(wac)
            .apply(springSecurity())
            .build();
  }
}
