package cz.upce.fei.nnpia.pshop.service.items;

import cz.upce.fei.nnpia.pshop.entity.items.Tripod;
import cz.upce.fei.nnpia.pshop.repository.items.TripodRepository;
import org.springframework.stereotype.Service;

@Service
public class TripodService extends ItemService<Tripod> {

    public TripodService(TripodRepository tripodRepository) {
        super(tripodRepository);
    }
}
