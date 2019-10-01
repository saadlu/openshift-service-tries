package net.saad.tries.servicetries.model;

import org.springframework.data.annotation.Id;

public class GiftToStore {

    @Id
    private String id;
    private final String name;

    public GiftToStore(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}
