package project.product_service_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import project.product_service_project.dto.ProductDTO;
import project.product_service_project.model.Product;
import project.product_service_project.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProduct(){
        return repo.findAll();
    }


    public Product DtoToProduct(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setBrand(dto.getBrand());
        product.setStock(dto.getStock());
        return product;
    }
    public ProductDTO productToDto(Product product) {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setBrand(product.getBrand());
        productDTO.setStock(product.getStock());

        return productDTO;

    }

    public ProductDTO createProduct(ProductDTO dto){
        Product product=DtoToProduct(dto);
        Product savedproduct=repo.save(product);
        return productToDto(savedproduct);

    }

    public Product getById(Long id){
        Product products= repo.findById(id).
                orElseThrow(()-> new RuntimeException("product not found"));
        return products;
    }

    public String deleteById(Long id){
        Product exit=repo.findById(id).
                orElseThrow(()-> new RuntimeException("product not found"));
        repo.deleteById(id);
        return "Product Delete By Id ";
    }

    public List<Product> searchProduct(String productName){
        return repo.findByProductNameContainingIgnoreCase(productName);
    }

}
