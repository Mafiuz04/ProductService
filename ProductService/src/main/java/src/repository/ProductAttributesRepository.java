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

    @Query("SELECT COUNT(a) > 0 FROM ProductAttributes a WHERE a.type = :type AND a.attributeName = :attributeName AND a.attributeValue = :attributeValue")
    boolean existsByAttributes(@Param("type") String type,
                               @Param("attributeName") String attributeName,
                               @Param("attributeValue") String attributeValue);
}
