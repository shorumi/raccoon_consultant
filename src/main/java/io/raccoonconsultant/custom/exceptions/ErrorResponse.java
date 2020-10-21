package io.raccoonconsultant.custom.exceptions;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonRootName("error")
public class ErrorResponse {

  private String message;
  private List<String> details;
}
