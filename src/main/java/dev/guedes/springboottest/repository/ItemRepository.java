package dev.guedes.springboottest.repository;

import dev.guedes.springboottest.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author João Guedes
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByOrderId(Long orderId);

}
