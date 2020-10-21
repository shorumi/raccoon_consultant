package io.raccoonconsultant.custom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -1071248154736711215L;

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
