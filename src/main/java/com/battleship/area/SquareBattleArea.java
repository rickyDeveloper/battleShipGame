package com.battleship.area;

import com.battleship.coordinate.Coordinate;
import com.battleship.coordinate.TwoDimensionalCoordinate;
import com.battleship.exception.OverlappingCoordinateException;
import com.battleship.ship.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.IntStream;

/**
 * A square battle are
 * Created by vikasnaiyar on 08/09/18.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SquareBattleArea implements BattleArea {

    private Coordinate[][] coordinates;

    @Autowired
    public SquareBattleArea(Coordinate[][] coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * This is mainly written for testing purpose
     *
     * @return
     */
    @Override
    public int getBattleArea() {
        if (coordinates == null) {
            return 0;
        }
        return coordinates.length * coordinates[0].length;
    }

    /**
     * While parking ship I am not considering overlapping coordinates
     * by two ships as problem statement clearly mentions that two ships
     * will not be have overlapping coordinates.
     *
     * @param ship
     * @param startCoordinate
     * @return
     */
    @Override
    public Collection<Coordinate> placeShip(Ship ship, Coordinate startCoordinate) {
        Collection<Coordinate> allocatedCoordinates = new HashSet<>();

        int startXIndex = ((TwoDimensionalCoordinate) startCoordinate).getX() - 1;
        int startYIndex = ((TwoDimensionalCoordinate) startCoordinate).getY() - 'A';

        int shipWidth = ship.getWidth();
        int shipHeight = ship.getHeight();


        IntStream.range(startXIndex, shipWidth + startXIndex).forEach(i -> {
                    IntStream.range(startYIndex, shipHeight + startYIndex).forEach(j -> {
                        if (((TwoDimensionalCoordinate) coordinates[i][j]).isOccupied()) {
                                System.err.println("Pre-occupied coordinate found at " + coordinates[i][j].toString());
                                throw new RuntimeException("Pre-occupied coordinate found at " + coordinates[i][j].toString());
                        }

                        // add the coordinates in allocation list
                        allocatedCoordinates.add(coordinates[i][j]);
                        ((TwoDimensionalCoordinate) coordinates[i][j]).setOccupied(true);
                    });
                }
        );

        return allocatedCoordinates;
    }
}
