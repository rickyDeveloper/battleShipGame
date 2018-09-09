package com.battleship.coordinate;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
public interface DestroyableCoordinate extends Coordinate {
    boolean hit(Coordinate coordinate);
    boolean isDestroyed();
}
