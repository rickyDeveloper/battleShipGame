package com.battleship.config;

import com.battleship.area.BattleArea;
import com.battleship.area.SquareBattleArea;
import com.battleship.coordinate.Coordinate;
import com.battleship.coordinate.TwoDimensionalCoordinate;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
@Configuration
public class SquareBattleAreaConfig {

    private static char[] y_coordinates = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                                   'K', 'L','M', 'N', 'O','P', 'Q', 'R', 'S', 'T' ,
                                    'U', 'V', 'W', 'X', 'Y' , 'Z'};

    @Value("${battlearea.width}")
    private int width;

    @Value("${battlearea.height}")
    private char height;

    @Bean
    public BattleArea battleArea() {
        return new SquareBattleArea(coordinates());
    }

    /**
     * Coordinates for the battle area
     * @return
     */
    @Bean
    public Coordinate[][] coordinates() {

        int heightInInteger = height - y_coordinates[0] + 1;

        Coordinate[][] coordinates = new TwoDimensionalCoordinate[width][heightInInteger];

        IntStream.range(0,width).forEach(i ->
                IntStream.range(0,heightInInteger).forEach(j -> {
                    coordinates[i][j] = getTwoDimensionalCoordinate(y_coordinates[j], i+1);
                })
        );

        return coordinates;
    }

    @Lookup
    public TwoDimensionalCoordinate getTwoDimensionalCoordinate(char y, int x) {
        return null;
    }

}
