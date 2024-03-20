package dev.guedes.springboottest.dto;

import dev.guedes.springboottest.model.Account;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * @author João Guedes
 */
@Getter
public class OrderDTO {

    @NotNull
    private Account account;

}
