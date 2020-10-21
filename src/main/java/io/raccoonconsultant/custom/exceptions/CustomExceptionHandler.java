package io.raccoonconsultant.custom.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest webRequest) {
    List<String> details = new ArrayList<>();
    details.add(e.getLocalizedMessage());
    ErrorResponse errorResponse = new ErrorResponse("Server errors", details);

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<Object> handleUserNotFoundException(ResourceNotFoundException e, WebRequest webRequest) {
    List<String> details = new ArrayList<>();
    details.add(e.getLocalizedMessage());
    ErrorResponse errorResponse = new ErrorResponse("Record not found", details);

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException e,
          HttpHeaders httpHeaders,
          HttpStatus httpStatus,
          WebRequest webRequest
  ) {
    List<String> details = new ArrayList<>();
    for(ObjectError error : e.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    ErrorResponse errorResponse = new ErrorResponse("Validation Failed", details);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

  }
}
