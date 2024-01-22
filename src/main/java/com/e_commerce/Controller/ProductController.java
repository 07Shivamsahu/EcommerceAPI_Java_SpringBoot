package com.e_commerce.Controller;

import com.e_commerce.Entity.Product;
import com.e_commerce.Entity.SearchForm;
import com.e_commerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequestMapping("/web/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping
    public String getAllProducts(@ModelAttribute("search") SearchForm searchForm, Model model){

        List<Product> products;

        if (searchForm != null && searchForm.getTitle() != null && !searchForm.getTitle().isEmpty()) {
            // If the search form has a title, perform a title-based search
            products = productService.findByTitleContainingIgnoreCase(searchForm.getTitle());
        } else {
            // Otherwise, retrieve all products
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products);
        return "products";
    }
    @ModelAttribute("search")
    public SearchForm getSearchForm() {
        return new SearchForm();
    }
}
