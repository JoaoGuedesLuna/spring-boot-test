package dev.guedes.springboottest.service;

import dev.guedes.springboottest.model.Item;
import dev.guedes.springboottest.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author João Guedes
 */
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Object save(Item item) {
        return this.itemRepository.save(item);
    }

    public Optional<Item> findById(Long id) {
        return this.itemRepository.findById(id);
    }

    public List<Item> findAllByOrderId(Long orderId) {
        return this.itemRepository.findAllByOrderId(orderId);
    }

    @Transactional
    public void delete(Item item) {
        this.itemRepository.delete(item);
    }

}
