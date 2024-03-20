package dev.guedes.springboottest.controller;

import dev.guedes.springboottest.dto.AccountDTO;
import dev.guedes.springboottest.model.Account;
import dev.guedes.springboottest.service.AccountService;
import dev.guedes.springboottest.util.ResponseNotFound;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * @author João Guedes
 */
@RestController
@RequestMapping("/spring-boot-test/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("save")
    public ResponseEntity<Object> save(@RequestBody @Valid AccountDTO accountDTO) {
        if (this.accountService.existsByEmail(accountDTO.getEmail())) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Email is already in use");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.accountService.save(account));
    }

    @GetMapping("/find/id")
    public ResponseEntity<Object> findById(@RequestParam("id") Long id) {
        Optional<Account> accountOptional = this.accountService.findById(id);
        if (accountOptional.isEmpty()) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Account not found");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        return ResponseEntity.status(HttpStatus.OK).body(accountOptional.get());
    }

    @GetMapping("/find/email")
    public ResponseEntity<Object> findByEmail(@RequestParam("email") String email) {
        Optional<Account> accountOptional = this.accountService.findByEmail(email);
        if (accountOptional.isEmpty()) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Account not found");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        return ResponseEntity.status(HttpStatus.OK).body(accountOptional.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid AccountDTO accountDTO) {
        Optional<Account> accountOptional = this.accountService.findById(id);
        if (accountOptional.isEmpty()) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Account not found");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        Account account = accountOptional.get();
        account.setPassword(accountDTO.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(this.accountService.save(account));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        Optional<Account> accountOptional = this.accountService.findById(id);
        if (accountOptional.isEmpty()) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Account not found");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        this.accountService.delete(accountOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Account deleted: ".concat(accountOptional.get().toString()));
    }

}
