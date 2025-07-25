package project.product_service_project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.product_service_project.dto.ProductDTO;
import project.product_service_project.model.Product;
import project.product_service_project.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> view(){
        return service.getAllProduct();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam String productName){
        return service.searchProduct(productName);
    }

    @PostMapping("/insert")
    public ProductDTO createProduct(@RequestBody ProductDTO dto ){
        return service.createProduct(dto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id){
        return service.deleteById(id);
    }
}
