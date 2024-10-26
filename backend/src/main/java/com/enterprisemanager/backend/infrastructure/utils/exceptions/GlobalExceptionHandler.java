package com.enterprisemanager.backend.infrastructure.utils.exceptions;

import com.enterprisemanager.backend.domain.dtos.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleObjectNotFoundException(ObjectNotFoundException exception) {
        ApiError error = new ApiError();
        error.setMessage("El recurso no fue encontrado");
        error.setBackedMessage(exception.getLocalizedMessage());
        error.setTime(LocalDateTime.now());
        error.setHttpCode(404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        ApiError error = new ApiError();
        error.setMessage("Error: la petición enviada posee un formato incorrecto");
        error.setBackedMessage(exception.getLocalizedMessage());
        error.setTime(LocalDateTime.now());
        error.setHttpCode(400);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({AccessDeniedException.class, AuthenticationException.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleUnauthorizedException(HttpServletRequest request, AccessDeniedException exception) {
        ApiError error = new ApiError();
        error.setBackedMessage(exception.getLocalizedMessage());
        error.setUrl(request.getRequestURL().toString());
        error.setMethod(request.getMethod());
        error.setMessage("Usted no está autorizado para usar esta función...");
        error.setTime(LocalDateTime.now());
        error.setHttpCode(403);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }


    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handlerGenericException(Exception exception) {
        ApiError error = new ApiError();
        error.setMessage("Error interno en el servidor, vuelva a intentarlo");
        error.setBackedMessage(exception.getLocalizedMessage());
        error.setTime(LocalDateTime.now());
        error.setHttpCode(500);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
