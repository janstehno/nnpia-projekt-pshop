package cz.upce.fei.nnpia.pshop.repository.items;

import cz.upce.fei.nnpia.pshop.entity.items.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository<I extends Item> extends JpaRepository<I, Long> {}