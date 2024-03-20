package dev.guedes.springboottest.controller;

import dev.guedes.springboottest.dto.ProductDTO;
import dev.guedes.springboottest.model.Product;
import dev.guedes.springboottest.service.ProductService;
import dev.guedes.springboottest.util.ResponseNotFound;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
@Controller
@RequestMapping("/spring-boot-test/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("save")
    public ResponseEntity<Object> save(@RequestBody @Valid ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.save(product));
    }

    @GetMapping("/find/id")
    public ResponseEntity<Object> findById(@RequestParam("id") Long id) {
        Optional<Product> productOptional = this.productService.findById(id);
        if (productOptional.isEmpty()) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Product not found");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
    }

    @GetMapping("/find/s")
    public ResponseEntity<Object> findAllByNameStartingWith(@RequestParam("name") String name) {
        List<Product> products = this.productService.findAllByNameStartingWith(name);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/find/c")
    public ResponseEntity<Object> findAllByNameContainingAndPriceBetween(@RequestParam("name") String name,
                                                                         @RequestParam("min_price") BigDecimal minPrice,
                                                                         @RequestParam("max_price") BigDecimal maxPrice) {
        List<Product> products = this.productService.findAllByNameContainingAndPriceBetween(name, minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid ProductDTO productDTO) {
        Optional<Product> productOptional = this.productService.findById(id);
        if (productOptional.isEmpty()) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Product not found");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        Product product = productOptional.get();
        BeanUtils.copyProperties(productDTO, product);
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.save(product));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        Optional<Product> productOptional = this.productService.findById(id);
        if (productOptional.isEmpty()) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Product not found");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        this.productService.delete(productOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted: ".concat(productOptional.get().toString()));
    }

}
