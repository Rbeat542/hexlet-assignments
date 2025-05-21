package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    @Query("SELECT e FROM Product e WHERE (:min IS NULL or e.price >= :min) AND (:max IS NULL OR e.price <= :max)")
    List<Product> findAllByPriceRange(@Param("min") Integer min, @Param("max") Integer max,  @Param("sort") Sort sort);
    // END
}
