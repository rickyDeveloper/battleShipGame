package com.battleship.area;

import com.battleship.coordinate.Coordinate;
import com.battleship.ship.Ship;

/**
 * Created by vikasnaiyar on 08/09/18.
 */
public interface BattleArea {

    boolean parkShip(Ship ship , Coordinate coordinate);

    /**
     * This is mainly written for testing purpose
     * @return
     */
    int getBattleAreaUnits();

}
