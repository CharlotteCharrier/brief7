package fr.simplon.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.simplon.api.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsernameAndPassword(String username, String password);

    // si les mots clés des méthodes générées par sprint bot sont pas suffisants,
    // je peux créer ma propre méthode avec ma propre requête comme ça:

    // @Query(value = "SELECT * FROM 'users' WHERE username = $1", nativeQuery=true)
    // public Optional<User> bichette(String biche);
}
