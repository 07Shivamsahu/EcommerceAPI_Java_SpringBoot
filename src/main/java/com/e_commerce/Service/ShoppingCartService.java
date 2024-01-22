package com.e_commerce.Service;

import com.e_commerce.DTO.ProductDto;
import com.e_commerce.Entity.CartItem;
import com.e_commerce.Entity.Product;
import com.e_commerce.Entity.ShoppingCart;
import com.e_commerce.Entity.User;
import com.e_commerce.Repository.CartItemRepository;
import com.e_commerce.Repository.ShoppingCartRepository;
import com.e_commerce.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductService productService;


    @Autowired
    public ShoppingCartService(CartItemRepository cartItemRepository, ShoppingCartRepository shoppingCartRepository, UserRepository userRepository) {
        this.cartItemRepository=cartItemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }

    // For adding product to cartitem

//    public void addProductToCart(Long userId, ProductDto productDto){
//        // Retrive the user and product
//        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
//        Product product = productService.getProductById(productDto.getProductId());
//
//        // Check if product exist
//
//        if (product == null) {
//            throw new EntityNotFoundException("Product not found");
//        }
//
//        // Create new cart item and set its attributes
//        CartItem cartItem = new CartItem();
//        cartItem.setUser(user);
//        cartItem.setProduct(product);
//        cartItem.setQuantity(productDto.getQuantity());
//
//        // save the cart item to the repository
//        cartItemRepository.save(cartItem);
//    }
public List<CartItem> getCartItems(Long userid){
    return cartItemRepository.findByUserId(userid);
}


    public Optional<ShoppingCart> getShoppingCartById(Long userid) {
        return shoppingCartRepository.findById(userid);
    }

    public ShoppingCart createShoppingCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartRepository.save(shoppingCart);
    }
    public void addToShoppingCart(Long shoppingCartId, CartItem cartItem) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found"));
        shoppingCart.addCartItem(cartItem);
        shoppingCartRepository.save(shoppingCart);
    }

    public void removeFromShoppingCart(Long shoppingCartId, Long cartItemId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingCart not found"));
        CartItem cartItem = shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("CartItem not found in the ShoppingCart"));
        shoppingCart.removeCartItem(cartItem);
        shoppingCartRepository.save(shoppingCart);
    }
}
