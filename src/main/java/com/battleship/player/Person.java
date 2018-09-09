package com.battleship.player;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Person {

    public String getName() {
        return name;
    }

    private String name;

    public Person(String name) {
        this.name = name;
    }
}
