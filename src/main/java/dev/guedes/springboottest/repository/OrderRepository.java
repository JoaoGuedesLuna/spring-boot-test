package dev.guedes.springboottest.repository;

import dev.guedes.springboottest.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author João Guedes
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByAccountId(Long accountId);

}
