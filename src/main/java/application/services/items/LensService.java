package application.services.items;

import application.entities.items.Lens;
import application.repositories.items.LensRepository;
import org.springframework.stereotype.Service;

@Service
public class LensService extends ItemService<Lens> {

    public LensService(LensRepository lensRepository) {
        super(lensRepository);
    }
}
