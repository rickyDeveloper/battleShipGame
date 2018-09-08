package com.battleship.coordinate;

import com.battleship.coordinate.Coordinate;

/**
 * Created by vikasnaiyar on 08/09/18.
 */
public class TwoDimensionalCoordinate implements Coordinate {

    private int x;

    private char y;

    public TwoDimensionalCoordinate(char y, int x) {
        this.x = x;
        this.y = y;
    }

}
