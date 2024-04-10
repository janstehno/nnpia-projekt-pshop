package cz.upce.fei.nnpia.pshop.controller.items;

import cz.upce.fei.nnpia.pshop.entity.items.Tripod;
import cz.upce.fei.nnpia.pshop.service.items.TripodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tripods")
public class TripodController extends ItemController<Tripod> {

    public TripodController(TripodService service) {
        super(service);
    }
}

