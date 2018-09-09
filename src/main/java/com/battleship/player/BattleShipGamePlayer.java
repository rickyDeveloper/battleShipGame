package com.battleship.player;

import com.battleship.coordinate.Coordinate;
import com.battleship.coordinate.DestroyableCoordinate;
import com.battleship.coordinate.ShipCoordinate;
import com.battleship.ship.Ship;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
public class BattleShipGamePlayer implements Player {
    private Person person;
    private Map<Ship, Collection<DestroyableCoordinate>> shipCoordinatesMap;
    private List<Coordinate> missileTargets;

    public BattleShipGamePlayer(Person person, Map<Ship, Collection<DestroyableCoordinate>> shipCoordinatesMap, List<Coordinate> missileTargets) {
        this.person = person;
        this.shipCoordinatesMap = shipCoordinatesMap;
        this.missileTargets = missileTargets;
    }

    @Override
    public String getName() {
        return person.getName();
    }

    @Override
    public boolean hasMissileHitTarget(Coordinate coordinate) {
        return shipCoordinatesMap.values().stream().anyMatch(v -> v.stream().anyMatch(dc -> dc.hit(coordinate)));
    }

    @Override
    public boolean hasNextMissile() {
        return missileTargets.size() > 0;
    }

    @Override
    public Coordinate getNextMissileTarget() {
        Coordinate coordinate = null;
        if (!missileTargets.isEmpty()) {
            coordinate = missileTargets.get(0);
            missileTargets.remove(0);
        }
        return coordinate;
    }

    @Override
    public boolean areAllShipsDestroyed() {
        return shipCoordinatesMap.values().stream().allMatch(v -> v.stream().allMatch(dc -> dc.isDestroyed()));
    }
}
