package com.battleship.area;

import com.battleship.coordinate.Coordinate;
import com.battleship.ship.Ship;

import java.util.Collection;

/**
 * Interface for representing a battle area
 * Created by vikasnaiyar on 08/09/18.
 */
public interface BattleArea {

    Collection<Coordinate> parkShip(Ship ship , Coordinate startCoordinate);

    /**
     * This is mainly written for testing purpose
     * @return
     */
    int getBattleArea();

}
