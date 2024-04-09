package application.controllers.items;

import application.entities.items.Lens;
import application.entities.items.Tripod;
import application.services.items.LensService;
import application.services.items.TripodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tripods")
public class TripodController extends ItemController<Tripod> {

    public TripodController(TripodService service) {
        super(service);
    }
}

