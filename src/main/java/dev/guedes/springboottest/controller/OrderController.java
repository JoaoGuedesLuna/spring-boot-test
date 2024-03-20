package dev.guedes.springboottest.controller;

import dev.guedes.springboottest.dto.OrderDTO;
import dev.guedes.springboottest.model.Order;
import dev.guedes.springboottest.service.OrderService;
import dev.guedes.springboottest.util.ResponseNotFound;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
@Controller
@RequestMapping("/spring-boot-test/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("save")
    public ResponseEntity<Object> save(@RequestBody @Valid OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.orderService.save(order));
    }

    @GetMapping("/find/id")
    public ResponseEntity<Object> findById(@RequestParam("id") Long id) {
        Optional<Order> orderOptional = this.orderService.findById(id);
        if (orderOptional.isEmpty()) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Order not found");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderOptional.get());
    }

    @GetMapping("/find/account")
    public ResponseEntity<Object> findAllByAccountId(@RequestParam("id") Long accountId) {
        List<Order> orders = this.orderService.findAllByAccountId(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        Optional<Order> orderOptional = this.orderService.findById(id);
        if (orderOptional.isEmpty()) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Order not found");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        this.orderService.delete(orderOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted: ".concat(orderOptional.get().toString()));
    }

}
