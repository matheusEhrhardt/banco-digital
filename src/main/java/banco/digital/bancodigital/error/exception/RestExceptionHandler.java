package banco.digital.bancodigital.error.exception;

import banco.digital.bancodigital.error.ErroMessage;
import banco.digital.bancodigital.error.handler.ResourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleResourseNotFoudException(ResourseNotFoundException ex){

        ErroMessage error = new ErroMessage("Not Found", HttpStatus.NOT_FOUND.value(),ex.getMessage() );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}