package net.saad.tries.servicetries.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeadController {

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Object> head() {
        return ResponseEntity.ok().build();
    }
}
