package dev.guedes.springboottest.service;

import dev.guedes.springboottest.model.Product;
import dev.guedes.springboottest.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Object save(Product product) {
        return this.productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    public List<Product> findAllByNameStartingWith(String name) {
        return this.productRepository.findAllByNameStartingWith(name);
    }

    public List<Product> findAllByNameContainingAndPriceBetween(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        return this.productRepository.findAllByNameContainingAndPriceBetween(name, minPrice, maxPrice);
    }

    @Transactional
    public void delete(Product product) {
        this.productRepository.delete(product);
    }

}
