package dev.guedes.springboottest.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import java.math.BigDecimal;

/**
 * @author João Guedes
 */
@Getter
public class ProductDTO {

    @NotBlank
    @Size(max=50)
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @Digits(integer=10, fraction=2)
    @DecimalMin(value="0.00", inclusive=false)
    private BigDecimal price;

}
