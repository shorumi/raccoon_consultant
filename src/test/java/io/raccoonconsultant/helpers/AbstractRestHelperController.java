package br.com.dasa.labexam.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestHelperController {

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
