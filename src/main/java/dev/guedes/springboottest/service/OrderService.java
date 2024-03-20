package dev.guedes.springboottest.service;

import dev.guedes.springboottest.model.Order;
import dev.guedes.springboottest.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Object save(Order order) {
        return this.orderRepository.save(order);
    }

    public Optional<Order> findById(Long id) {
        return this.orderRepository.findById(id);
    }

    public List<Order> findAllByAccountId(Long accountId) {
        return this.orderRepository.findAllByAccountId(accountId);
    }

    @Transactional
    public void delete(Order order) {
        this.orderRepository.delete(order);
    }

}
