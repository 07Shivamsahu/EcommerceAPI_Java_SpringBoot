package com.e_commerce.Controller;

import com.e_commerce.DTO.ProductDto;
import com.e_commerce.Entity.CartItem;
import com.e_commerce.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartitems")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/{userid}")
    public ResponseEntity<String> addToCart(@PathVariable Long userid, @RequestBody ProductDto productDto){
        cartItemService.addToCartItem(userid, productDto);
        return new ResponseEntity<>("Item added to the cart of user with ID: "+ userid, HttpStatus.OK);
    }

    @GetMapping("{userid}")
    public List<CartItem> getCartItems(@PathVariable Long userid) {
        return cartItemService.getCartItems(userid);
    }
}
