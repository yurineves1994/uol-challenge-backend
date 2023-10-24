package uolbackendd.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ExceptionDTO> threatNoSuchElementException(NoSuchElementException noSuchElementException) {
    ExceptionDTO dto = new ExceptionDTO("Essa lista não possui mais usuarios disponiveis!", "400");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
  }
  
}
