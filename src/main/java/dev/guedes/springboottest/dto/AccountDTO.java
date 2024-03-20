package dev.guedes.springboottest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * @author João Guedes
 */
@Getter
public class AccountDTO {

    @Email
    @NotBlank
    @Size(max=256)
    private String email;
    @NotBlank
    @Size(min=8, max=50)
    private String password;

}
