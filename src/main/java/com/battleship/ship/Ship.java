package com.battleship.ship;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
public interface Ship {

    int getWidth();

    int getHeight();

    default ShipType getType() {
        return null;
    }
}
