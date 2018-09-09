package com.battleship.area;

import com.battleship.coordinate.Coordinate;
import com.battleship.ship.Ship;

import java.util.Collection;

/**
 * Interface for representing a battle area
 *
 * In general battle area could of any size.
 *
 *
 * Created by vikasnaiyar on 08/09/18.
 */
public interface BattleArea {

    /**
     * Place a ship in battle area.
     *
     * At present, I am not checking for overlapping area of ships
     * @param ship
     * @param startCoordinate
     * @return
     */
    Collection<Coordinate> placeShip(Ship ship , Coordinate startCoordinate);

    /**
     * This is mainly written for testing purpose
     * Not meant for program
     * @return
     */
    int getBattleArea();

}
