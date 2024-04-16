package cz.upce.fei.nnpia.pshop.service.items;

import cz.upce.fei.nnpia.pshop.entity.items.Item;
import cz.upce.fei.nnpia.pshop.repository.items.ItemRepository;
import cz.upce.fei.nnpia.pshop.service.ServiceI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public abstract class ItemService<I extends Item> implements ServiceI<I> {

    private final ItemRepository<I> repository;

    @Override
    public List<I> getAll() {
        return repository.findAll();
    }

    public Page<I> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public I getById(Long id) {
        Optional<I> item = repository.findById(id);
        return item.orElse(null);
    }

    @Override
    public I create(I item) {
        return repository.save(item);
    }

    @Override
    public I update(I item) {
        return repository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
