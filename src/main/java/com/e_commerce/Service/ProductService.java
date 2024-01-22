package com.e_commerce.Service;

import com.e_commerce.Entity.Product;
import com.e_commerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findByTitleContainingIgnoreCase(String title) {
        return productRepository.findByTitleContainingIgnoreCase(title);
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    public Product getProductById(Long productId){
        return productRepository.findById(productId).orElse(null);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


}
