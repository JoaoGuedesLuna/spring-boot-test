package dev.guedes.springboottest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * @author João Guedes
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name="Products")
@Table(name="Products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;
    @Column(length=50, nullable = false)
    private String name;
    @Column(columnDefinition="TEXT", nullable = false)
    private String description;
    @Column(precision=10, scale=2, nullable = false)
    private BigDecimal price;

}
