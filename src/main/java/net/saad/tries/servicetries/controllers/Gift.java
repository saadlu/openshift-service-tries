package net.saad.tries.servicetries.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gift {
    private final String name;

    public Gift(@JsonProperty(value = "name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
