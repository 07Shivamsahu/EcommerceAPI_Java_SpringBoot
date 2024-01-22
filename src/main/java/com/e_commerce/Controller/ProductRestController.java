package com.e_commerce.Controller;

import com.e_commerce.Entity.Product;
import com.e_commerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }


    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId){
        return productService.getProductById(productId);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
    }
}
