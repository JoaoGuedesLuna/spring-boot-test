package dev.guedes.springboottest.controller;

import dev.guedes.springboottest.dto.ItemDTO;
import dev.guedes.springboottest.model.Item;
import dev.guedes.springboottest.service.ItemService;
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
@RequestMapping("/spring-boot-test/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("save")
    public ResponseEntity<Object> save(@RequestBody @Valid ItemDTO itemDTO) {
        Item item = new Item();
        BeanUtils.copyProperties(itemDTO, item);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.itemService.save(item));
    }

    @GetMapping("/find/id")
    public ResponseEntity<Object> findById(@RequestParam("id") Long id) {
        Optional<Item> itemOptional = this.itemService.findById(id);
        if (itemOptional.isEmpty()) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Item not found");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        return ResponseEntity.status(HttpStatus.OK).body(itemOptional.get());
    }

    @GetMapping("/find/order")
    public ResponseEntity<Object> findAllByAccountId(@RequestParam("id") Long orderId) {
        List<Item> items = this.itemService.findAllByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        Optional<Item> itemOptional = this.itemService.findById(id);
        if (itemOptional.isEmpty()) {
            ResponseNotFound responseNotFound = ResponseNotFound.getInstance();
            responseNotFound.setMessage("Item not found");
            return ResponseEntity.status(responseNotFound.getHttpStatus()).body(responseNotFound);
        }
        this.itemService.delete(itemOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Item deleted: ".concat(itemOptional.get().toString()));
    }

}
