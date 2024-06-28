package fr.simplon.api.controllers;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import lombok.Getter;
import lombok.Setter;

@ControllerAdvice  //écoute le résultat de nos controllers et si le résultat c'est exception il exécute ce code
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //le .class permet de récupérer un objet qui a cette class
    public ResponseEntity<?> handleException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setType(ex.getClass().getName()); //nous donne en String le type de l'erreur
        errorDetails.setMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(errorDetails, resolveAnnotationResponseStatus(ex));
    }

    private HttpStatus resolveAnnotationResponseStatus(Exception ex) {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        return responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}

@Getter @Setter
class ErrorDetails {
    String type;
    String message;
}
