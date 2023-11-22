package org.example.app.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.Greeting;
import org.example.Greeting.Type;
import org.example.app.manager.GreetingPluginManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class GreetingController {

    private final List<Greeting> greetings;
    private final GreetingPluginManager greetingPluginManager;

    @GetMapping
    public List<String> getGreeting() {
        List<String> result = new ArrayList<>();
        for (Greeting greeting : greetings) {
            result.add(greeting.getGreeting());
        }
        return result;
    }

    @GetMapping("/{type}")
    public String getGreeting(@PathVariable String type) {
        return greetingPluginManager.getGreetingByType(getType(type)).getGreeting();
    }

    private Type getType(String type) {
        for (Type value : Type.values()) {
            if (type.toUpperCase().equals(value.toString())) {
                return value;
            }
        }
        return null;
    }


}
