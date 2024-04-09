package application.controllers.items;

import application.entities.items.Camera;
import application.entities.items.Item;
import application.services.items.CameraService;
import application.services.items.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cameras")
public class CameraController extends ItemController<Camera> {

    public CameraController(CameraService service) {
        super(service);
    }
}

