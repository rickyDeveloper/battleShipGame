package com.battleship.player;

import com.battleship.coordinate.Coordinate;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
public interface Player {

    String getName();

    boolean hasMissileHitTarget(Coordinate coordinate);

    boolean hasNextMissile();

    Coordinate getNextMissileTarget();

    boolean areAllShipsDestroyed();

}
