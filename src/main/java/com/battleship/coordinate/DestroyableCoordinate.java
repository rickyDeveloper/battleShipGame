package com.battleship.coordinate;

/**
 * In general not all coordinate will be destroyable
 * i.e. have some state of itself.
 * Hence, created a new interface for granular interfaces.
 * Created by vikasnaiyar on 09/09/18.
 */
public interface DestroyableCoordinate extends Coordinate {
    boolean hit(Coordinate coordinate);
    boolean isDestroyed();
}
