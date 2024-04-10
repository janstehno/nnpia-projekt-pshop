package cz.upce.fei.nnpia.pshop.controller.items;

import cz.upce.fei.nnpia.pshop.entity.items.Camera;
import cz.upce.fei.nnpia.pshop.service.items.CameraService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cameras")
public class CameraController extends ItemController<Camera> {

    public CameraController(CameraService service) {
        super(service);
    }
}

