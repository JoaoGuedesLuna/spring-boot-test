package dev.guedes.springboottest.dto;

import dev.guedes.springboottest.model.Order;
import dev.guedes.springboottest.model.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import java.math.BigDecimal;

/**
 * @author João Guedes
 */
@Getter
public class ItemDTO {

    @NotNull
    @Positive
    private int quantity;
    @NotNull
    @Digits(integer=10, fraction=2)
    @DecimalMin(value="0.00", inclusive=false)
    private BigDecimal unitPrice;
    @NotNull
    private Product product;
    @NotNull
    private Order order;

}
