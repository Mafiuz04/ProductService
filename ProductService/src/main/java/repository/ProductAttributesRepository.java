package repository;

import model.Product;
import model.ProductAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributesRepository extends JpaRepository<ProductAttributes, Long> {
}
