package net.saad.tries.servicetries.repository;

import net.saad.tries.servicetries.model.GiftToStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftsRepository extends CrudRepository<GiftToStore, String> {
}
