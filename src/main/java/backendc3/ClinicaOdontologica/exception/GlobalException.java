package backendc3.ClinicaOdontologica.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j
public class GlobalException {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException rnfe){
        log.error(rnfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" mensaje: "+rnfe.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> handleBadRequestException(BadRequestException bre){
        log.error(bre.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" mensaje: "+bre.getMessage());
    }
}