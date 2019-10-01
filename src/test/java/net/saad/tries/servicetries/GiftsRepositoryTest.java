package net.saad.tries.servicetries;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import net.saad.tries.servicetries.model.GiftToStore;
import net.saad.tries.servicetries.repository.GiftsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class GiftsRepositoryTest {

    @Autowired
    private GiftsRepository giftsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @AfterEach
    void tearDown() {
        mongoTemplate.getCollectionNames().forEach(mongoTemplate::dropCollection);
    }

    @Test
    void testStore() {
        GiftToStore stored = giftsRepository.save(new GiftToStore("toy"));

        List<GiftToStore> gifts = mongoTemplate.findAll(GiftToStore.class);

        assertThat(gifts).isNotEmpty();
        assertThat(gifts).hasSize(1);
        assertThat(gifts.get(0))
                .isEqualToComparingFieldByFieldRecursively(stored);
    }

    @Test
    void testGetAll() {
        GiftToStore toy = giftsRepository.save(new GiftToStore("toy"));
        GiftToStore shoes = giftsRepository.save(new GiftToStore("shoes"));
        GiftToStore shirt = giftsRepository.save(new GiftToStore("t-shirt"));

        Iterable<GiftToStore> all = giftsRepository.findAll();

        assertThat(all)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(toy, shoes, shirt);
    }

}
