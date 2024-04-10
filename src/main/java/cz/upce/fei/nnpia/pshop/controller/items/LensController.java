package cz.upce.fei.nnpia.pshop.controller.items;

import cz.upce.fei.nnpia.pshop.entity.items.Lens;
import cz.upce.fei.nnpia.pshop.service.items.LensService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lenses")
public class LensController extends ItemController<Lens> {

    public LensController(LensService service) {
        super(service);
    }
}

