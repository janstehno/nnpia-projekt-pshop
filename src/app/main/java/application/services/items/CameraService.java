package application.services.items;

import application.entities.items.Camera;
import application.repositories.items.CameraRepository;
import org.springframework.stereotype.Service;

@Service
public class CameraService extends ItemService<Camera> {

    public CameraService(CameraRepository cameraRepository) {
        super(cameraRepository);
    }
}
