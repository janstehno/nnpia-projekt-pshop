package application.controllers.items;

import application.entities.items.Item;
import application.services.items.ItemService;
import lombok.AllArgsConstructor;
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

