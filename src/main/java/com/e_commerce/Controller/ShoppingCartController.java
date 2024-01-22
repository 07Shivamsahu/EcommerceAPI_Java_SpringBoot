package com.e_commerce.Controller;

import com.e_commerce.DTO.ProductDto;
import com.e_commerce.Entity.CartItem;
import com.e_commerce.Entity.ShoppingCart;
import com.e_commerce.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shopping_carts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

//    @PostMapping("/{userid}/add-product")
//    public ResponseEntity<String> addProductToCart(@PathVariable Long userid, @RequestBody ProductDto productDto){
//        CartItem cartItem = convertToCartItem(productDto);
//        // call the service method to add product to the cart
//        shoppingCartService.addProductToCart(userid, productDto);
//        // Return a response with a status code of 200 (OK)
//        return new ResponseEntity<>("Item added to the cart of user with ID: "+ userid,HttpStatus.OK);
//    }
    // Helper method to convert ProductDto to CartItem
//    private CartItem convertToCartItem(ProductDto productDto) {
//        CartItem cartItem = new CartItem();
//        // Set other properties of CartItem from ProductDto
//        // (e.g., user, product, quantity)
//        return cartItem;
//    }

    @GetMapping("/{userid}")
    public ResponseEntity<List<CartItem>> getShoppingCart(@PathVariable Long userid) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartService.getShoppingCartById(userid);
        if(shoppingCartOptional.isPresent()){
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            List<CartItem> cartItems = shoppingCartService.getCartItems(userid);
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create/{userid}")
    public ResponseEntity<ShoppingCart> createShoppingCart(@PathVariable Long userid) {
        ShoppingCart createdShoppingCart = shoppingCartService.createShoppingCart(userid);
        return new ResponseEntity<>(createdShoppingCart, HttpStatus.CREATED);
    }

    @PostMapping("/{shoppingCartId}/add-item")
    public ResponseEntity<Void> addToShoppingCart(@PathVariable Long shoppingCartId, @RequestBody CartItem cartItem) {
        shoppingCartService.addToShoppingCart(shoppingCartId, cartItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{shoppingCartId}/remove-item/{cartItemId}")
    public ResponseEntity<Void> removeFromShoppingCart(@PathVariable Long shoppingCartId, @PathVariable Long cartItemId) {
        shoppingCartService.removeFromShoppingCart(shoppingCartId, cartItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
