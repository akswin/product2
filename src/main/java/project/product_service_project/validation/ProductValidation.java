package project.product_service_project.validation;

import org.springframework.stereotype.Component;
import project.product_service_project.dto.ProductDTO;

@Component
public class ProductValidation {

    public void validate(ProductDTO product) {

        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }

        if (product.getProductName().length() <= 2 || product.getProductName().length() > 50) {
            throw new IllegalArgumentException("Product name must be between 3 and 50 characters");
        }

        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be a positive number");
        }
        if (product.getPrice() < 0 || product.getPrice() >10000000){
            throw new IllegalArgumentException("price range 0 to 10000000");
        }

        if(product.getQuantity() == null || product.getQuantity()< 0){
            throw new IllegalArgumentException("Quantity must be 0 or Greater");
        }


        if (product.getBrand() == null || product.getBrand().trim().isEmpty()) {
            throw new IllegalArgumentException("Brand is required");
        }

        if (product.getBrand().length() < 2 || product.getBrand().length() > 30) {
            throw new IllegalArgumentException("Brand must be between 2 and 30 characters");
        }
    }


}
