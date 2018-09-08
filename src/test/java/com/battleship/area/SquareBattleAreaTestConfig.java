package com.battleship.area;

import com.battleship.coordinate.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

/**
 * Created by vikasnaiyar on 08/09/18.
 */
@TestConfiguration
public class SquareBattleAreaTestConfig {

    @Bean
    public BattleArea squareBattleArea(@Autowired Coordinate[][] coordinates) {
        return mock(SquareBattleArea.class);
    }

}
