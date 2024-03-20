package dev.guedes.springboottest.repository;

import dev.guedes.springboottest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author João Guedes
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameStartingWith(String name);

    List<Product> findAllByNameContainingAndPriceBetween(String name, BigDecimal minPrice, BigDecimal maxPrice);

}
