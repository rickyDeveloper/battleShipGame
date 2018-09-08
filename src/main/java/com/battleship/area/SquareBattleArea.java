package com.battleship.area;

import com.battleship.coordinate.Coordinate;
import com.battleship.ship.Ship;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by vikasnaiyar on 08/09/18.
 */
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
    public int getBattleAreaUnits() {
        if(coordinates == null) {
            return 0;
        }
        return coordinates.length * coordinates[0].length;
    }

    @Override
    public boolean parkShip(Ship ship , Coordinate coordinate) {
        return false;
    }
}
