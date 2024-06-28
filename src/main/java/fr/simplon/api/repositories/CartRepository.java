package fr.simplon.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.simplon.api.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
