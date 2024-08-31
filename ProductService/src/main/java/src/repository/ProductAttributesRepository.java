package src.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import src.model.ProductAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAttributesRepository extends JpaRepository<ProductAttributes, Long> {

    @Query("SELECT pa FROM ProductAttributes pa WHERE pa.type = :type")
    List<ProductAttributes> findByType(@Param("type") String type);
}
