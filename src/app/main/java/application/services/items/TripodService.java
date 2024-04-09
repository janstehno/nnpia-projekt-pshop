package application.services.items;

import application.entities.items.Tripod;
import application.repositories.items.TripodRepository;
import org.springframework.stereotype.Service;

@Service
public class TripodService extends ItemService<Tripod> {

    public TripodService(TripodRepository tripodRepository) {
        super(tripodRepository);
    }
}
