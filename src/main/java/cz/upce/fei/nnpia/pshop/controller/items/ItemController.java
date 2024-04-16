package cz.upce.fei.nnpia.pshop.controller.items;

import cz.upce.fei.nnpia.pshop.entity.items.Camera;
import cz.upce.fei.nnpia.pshop.entity.items.Item;
import cz.upce.fei.nnpia.pshop.service.items.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public abstract class ItemController<I extends Item> {

    private final ItemService<I> service;

    @GetMapping()
    public List<I> getAllItems() {
        return service.getAll();
    }

    @GetMapping("/pages")
    public Page<I> getPageItems(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<I> getItemById(
            @PathVariable
            Long id) {
        I item = service.getById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

