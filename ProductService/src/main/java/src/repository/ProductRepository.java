package src.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import src.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.model.ProductTypes;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Product p WHERE p.name = :name AND p.price = :price AND p.type = :type")
    boolean isItExistingProduct(@Param("name") String name,
                                               @Param("price") BigDecimal price,
                                               @Param("type") ProductTypes type);
}
