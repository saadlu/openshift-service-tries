package net.saad.tries.servicetries.controllers;

import net.saad.tries.servicetries.model.GiftToStore;
import net.saad.tries.servicetries.repository.GiftsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GiftsPostController {

    @Autowired
    private GiftsRepository giftsRepository;

    @PostMapping(path = "/gifts", consumes = "application/json")
    public ResponseEntity<Void> post(@RequestBody Gift gift) {
        giftsRepository.save(new GiftToStore(gift.getName()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
