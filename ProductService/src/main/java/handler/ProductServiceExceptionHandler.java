package handler;

import exception.ProductServiceException;
import model.ErrorMessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductServiceExceptionHandler {

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ErrorMessageDto> ProductServiceException(ProductServiceException ex){
        return ResponseEntity.status(ex.getStatus()).body(new ErrorMessageDto(ex.getStatus(), ex.getMessage()));
    }
}
