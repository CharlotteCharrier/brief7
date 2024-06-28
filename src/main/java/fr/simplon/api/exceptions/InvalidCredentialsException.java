package fr.simplon.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED) //sert à changer le code d'erreur + le nom de l'erreur 'unauthorized'
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException(Throwable exception) {
        super(exception);
    }

    public InvalidCredentialsException() {
        super();
    }
}
