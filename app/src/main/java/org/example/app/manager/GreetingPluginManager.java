package org.example.app.manager;

import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Greeting;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GreetingPluginManager {

    private final ApplicationContext applicationContext;

    private Map<Greeting.Type, Greeting> greetingMap;

    @PostConstruct
    private Map<Greeting.Type, Greeting> initGreetingMap() {
        log.info("-------------initGreetingMap-------------");
        greetingMap = applicationContext.getBeansOfType(Greeting.class)
            .values().stream().collect(
                Collectors.toMap(Greeting::getType, g -> g));
        for (Greeting value : greetingMap.values()) {
            log.info("getGreeting() = " + value.getGreeting());
        }
        return greetingMap;
    }

    public Greeting getGreetingByType(Greeting.Type type) {
        return greetingMap.get(type);
    }
}
