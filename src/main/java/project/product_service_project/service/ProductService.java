package project.product_service_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import project.product_service_project.dto.ProductDTO;
import project.product_service_project.model.Product;
import project.product_service_project.repository.ProductRepository;
import project.product_service_project.validation.ProductValidation;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private ProductValidation validation;

    public List<Product> getAllProduct(){
        return repo.findAll();
    }


    public Product DtoToProduct(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setBrand(dto.getBrand());
        product.setQuantity(dto.getQuantity());
        return product;
    }
    public ProductDTO productToDto(Product product) {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setBrand(product.getBrand());
        productDTO.setQuantity(product.getQuantity());

        productDTO.setStock(product.getQuantity() != null && product.getQuantity() > 0 ? "In Stock":"Out of Stock");

        return productDTO;

    }

    public ProductDTO createProduct(ProductDTO dto){
        validation.validate(dto);
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

    public Product updateProduct(Long id,Product updateProduct){
        Product existProduct=repo.findById(id).
                orElseThrow(() -> new RuntimeException("product not found with id:"+ id));

        ProductDTO dto= productToDto(updateProduct);
        validation.validate(dto);

        existProduct.setProductName(updateProduct.getProductName());
        existProduct.setBrand(updateProduct.getBrand());
        existProduct.setPrice(updateProduct.getPrice());
        existProduct.setQuantity(updateProduct.getQuantity());

        if (updateProduct.getQuantity() != null && updateProduct.getQuantity() > 0) {
            existProduct.setStock("In Stock");
        } else {
            existProduct.setStock("Out of Stock");
        }

        return repo.save(existProduct);
    }

}
