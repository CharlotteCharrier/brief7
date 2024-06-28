package fr.simplon.api.exceptions;

public class EntityNotFoundException extends RuntimeException {
    //Si je veux gérer un autre type d'exception j'ai juste à changer ce fichier
    //car comme cette class extends une autre class qui vient d'Exception
    //ça utilisera tout le reste du code de globalExceptionHandler et donc il
    //pourra être utilisé pour toutes les erreurs ou sous erreurs
}
