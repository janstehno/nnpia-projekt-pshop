package application.repositories.items;

import application.entities.items.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository<I extends Item> extends JpaRepository<I, Long> {}