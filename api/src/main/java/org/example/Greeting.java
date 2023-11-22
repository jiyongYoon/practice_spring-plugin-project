package org.example;

public interface Greeting {

    enum Type {
        WELCOME,
        BYE,
    }

    String getGreeting();

    Type getType();

}
