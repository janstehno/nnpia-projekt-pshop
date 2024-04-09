package application.services.items;

import application.entities.items.Item;
import application.repositories.items.ItemRepository;
import application.services.ServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class ItemService<I extends Item> implements ServiceI<I> {

    private final ItemRepository<I> itemRepository;

    public ItemService(ItemRepository<I> itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<I> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public I getById(Long id) {
        Optional<I> item = itemRepository.findById(id);
        return item.orElse(null);
    }

    @Override
    public I create(I item) {
        return itemRepository.save(item);
    }

    @Override
    public I update(I item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

}
