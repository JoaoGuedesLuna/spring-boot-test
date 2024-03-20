package dev.guedes.springboottest.service;

import dev.guedes.springboottest.model.Account;
import dev.guedes.springboottest.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * @author João Guedes
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Object save(Account account) {
        return this.accountRepository.save(account);
    }

    public Optional<Account> findById(Long id) {
        return this.accountRepository.findById(id);
    }

    public Optional<Account> findByEmail(String email) {
        return this.accountRepository.findByEmail(email);
    }

    @Transactional
    public void delete(Account account) {
        this.accountRepository.delete(account);
    }

    public boolean existsByEmail(String email) {
        return this.accountRepository.existsByEmail(email);
    }

}
