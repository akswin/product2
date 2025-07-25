package project.product_service_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.product_service_project.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductNameContainingIgnoreCase(String productName);

}
