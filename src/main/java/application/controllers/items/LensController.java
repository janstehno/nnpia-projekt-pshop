package application.controllers.items;

import application.entities.items.Camera;
import application.entities.items.Lens;
import application.services.items.ItemService;
import application.services.items.LensService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lenses")
public class LensController extends ItemController<Lens> {

    public LensController(LensService service) {
        super(service);
    }
}

