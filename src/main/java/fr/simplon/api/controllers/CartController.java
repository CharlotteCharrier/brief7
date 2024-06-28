package fr.simplon.api.controllers;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.simplon.api.messages.AddProductToCartMessage;
import fr.simplon.api.messages.CreatBookMessage;
import fr.simplon.api.messages.CreateCartMessage;
import fr.simplon.api.models.Cart;
import fr.simplon.api.models.Product;
import fr.simplon.api.models.User;
import fr.simplon.api.repositories.CartRepository;
import fr.simplon.api.repositories.ProductRepository;
import fr.simplon.api.repositories.UserRepository;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    //récupérer un panier par son id
    @GetMapping("/{id}")
    public Optional<Cart> getCartById(@PathVariable Integer id) {
        return cartRepository.findById(id);
    }

    //récupérer tous les paniers
    @GetMapping
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    //créer mon panier
    @PostMapping
    public Cart createCart(@RequestBody CreateCartMessage cart) throws Exception {
        User user = userRepository.findById(cart.getUser()).orElseThrow(() -> new Exception());
        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setDate(LocalDate.now());
        return cartRepository.save(newCart);
    }

    //ajouter un produit dans mon cart avec un DTO pour récupérer l'id de mon produit
    @PutMapping("/{id}/products")
    public Cart addProductToCart(@PathVariable Integer id, @RequestBody AddProductToCartMessage productId) {
        Cart currentCart = cartRepository.findById(id).get();
        Product newProduct = productRepository.findById(productId.getProductId()).get();
        currentCart.getProducts().add(newProduct);
        return cartRepository.save(currentCart);
    }

    //enlever un produit de mon panier
    @DeleteMapping("{cartId}/products/{productId}")
    public Cart removeProductFromCart(@PathVariable Integer cartId, @PathVariable Integer productId) {
        Cart currentCart = cartRepository.findById(cartId).get();
        Product product = productRepository.findById(productId).get();
        currentCart.getProducts().remove(product);
        return cartRepository.save(currentCart);
    }

    //transformer mon panier en commande
    @PatchMapping("{id}")
    public Cart transformCartIntoOrder(@PathVariable Integer id) {
        Cart cart = cartRepository.findById(id).get();
        cart.setIsOrdered(true);
        return cartRepository.save(cart);
    }

}
