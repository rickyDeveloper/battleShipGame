package com.battleship.config;

import com.battleship.area.BattleArea;
import com.battleship.area.SquareBattleArea;
import com.battleship.coordinate.Coordinate;
import com.battleship.coordinate.TwoDimensionalCoordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.stream.IntStream;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
@Configuration
public class SquareBattleAreaConfig {

    private char[] y_dimensions = {'A', 'B', 'C', 'D', 'E'};

    @Bean
    public BattleArea squareBattleArea() {
        return new SquareBattleArea(coordinates());
    }

    /**
     * Create dummy coordinates for testing.
     * @return
     */
    @Bean
    public Coordinate[][] coordinates() {
        Coordinate[][] coordinates = new TwoDimensionalCoordinate[3][3];

        IntStream.range(0,3).forEach(i ->
                IntStream.range(0,3).forEach(j -> {
                    coordinates[i][j] = new TwoDimensionalCoordinate(y_dimensions[j], i);
                })
        );

        return coordinates;
    }

}
