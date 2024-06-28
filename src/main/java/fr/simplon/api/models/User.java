package fr.simplon.api.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data //génère les getters et setters + un toString() pour
// afficher facilement nos classes dans la console + génère
// le @RequireArgsConstructor aussi en fonction des versions
@RequiredArgsConstructor // génère un constructeur avec tous
// les attributs NonNull
@Table(name="users") //empêche de prendre la valeur par défaut
// qui serait user (de la class User) à la création de la table
// car c'est un mot clé réservé à SQL
public class User { //crée la class dans la BDD

    @Id // déclare la primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // préciser que l'id sera auto incrémenté
    private int id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> cartList = new ArrayList<Cart>();

    public User() {

    }
}

//onetomany c'est sur les listes
//les many to one c'est sur le truc tout seul
//mappedBy() permet de faire un inner join et ça créé la référence créée avec