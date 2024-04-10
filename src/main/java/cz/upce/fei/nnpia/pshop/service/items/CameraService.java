package cz.upce.fei.nnpia.pshop.service.items;

import cz.upce.fei.nnpia.pshop.entity.items.Camera;
import cz.upce.fei.nnpia.pshop.repository.items.CameraRepository;
import org.springframework.stereotype.Service;

@Service
public class CameraService extends ItemService<Camera> {

    public CameraService(CameraRepository cameraRepository) {
        super(cameraRepository);
    }
}
