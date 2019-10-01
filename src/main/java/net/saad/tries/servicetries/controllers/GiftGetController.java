package net.saad.tries.servicetries.controllers;

import java.util.ArrayList;
import java.util.List;

import net.saad.tries.servicetries.repository.GiftsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GiftGetController {

    @Autowired
    private GiftsRepository giftsRepository;

    @GetMapping(value = "/gifts", produces = "application/json")
    public ResponseEntity<List<Gift>> getGifts() {
        List<Gift> gifts = new ArrayList<>();
        giftsRepository.findAll().forEach(g -> gifts.add(new Gift(g.getName())));
        return ResponseEntity.ok().body(gifts);
    }
}
