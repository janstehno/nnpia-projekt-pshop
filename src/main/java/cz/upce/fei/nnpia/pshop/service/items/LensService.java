package cz.upce.fei.nnpia.pshop.service.items;

import cz.upce.fei.nnpia.pshop.entity.items.Lens;
import cz.upce.fei.nnpia.pshop.repository.items.LensRepository;
import org.springframework.stereotype.Service;

@Service
public class LensService extends ItemService<Lens> {

    public LensService(LensRepository lensRepository) {
        super(lensRepository);
    }
}
