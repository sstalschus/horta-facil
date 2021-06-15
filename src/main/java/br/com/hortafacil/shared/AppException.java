package br.com.hortafacil.shared;

import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class AppException extends RuntimeException {
  public AppException(String exception) {
    super(exception);
  }
}
