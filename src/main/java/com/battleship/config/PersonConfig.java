package com.battleship.config;

import com.battleship.player.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
@Configuration
public class PersonConfig {

    @Bean
    public Person person1() {
        return new Person("Player-1");
    }

    @Bean
    public Person person2() {
        return new Person("Player-2");
    }

}
