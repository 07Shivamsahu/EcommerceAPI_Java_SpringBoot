package com.e_commerce.Service;

import com.e_commerce.DTO.ProductDto;
import com.e_commerce.Entity.CartItem;
import com.e_commerce.Entity.Product;
import com.e_commerce.Entity.User;
import com.e_commerce.Repository.CartItemRepository;
import com.e_commerce.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;



    public void addToCartItem(Long userid, ProductDto productDto){
         //Retrive the user and product
        User user = userRepository.findById(userid).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        Product product = productService.getProductById(productDto.getProductId());

        // Check if product exist

        if (product == null) {
            throw new EntityNotFoundException("Product not found");
        }

        // Create new cart item and set its attributes
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(productDto.getQuantity());

        // save the cart item to the repository
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems(Long userid){
        return cartItemRepository.findByUserId(userid);
    }
}
