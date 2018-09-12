package com.battleship.player;

import com.battleship.coordinate.Coordinate;
import com.battleship.coordinate.DestroyableCoordinate;
import com.battleship.coordinate.ShipCoordinate;
import com.battleship.ship.Ship;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * As per the problem statement Player need to notify
 * whether any of his ship was hit or not.
 *
 * Hence, I have improvised and provided additional
 * features to player. Ex:
 * Created by vikasnaiyar on 09/09/18.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BattleShipGamePlayer implements Player {
    private String name;
    private Map<Ship, Collection<DestroyableCoordinate>> shipCoordinatesMap;
    private List<Coordinate> missileTargets;

    public BattleShipGamePlayer(String name, Map<Ship, Collection<DestroyableCoordinate>> shipCoordinatesMap, List<Coordinate> missileTargets) {
        this.name = name;
        this.shipCoordinatesMap = shipCoordinatesMap;
        this.missileTargets = missileTargets;
    }

    @Override
    public String getName() {
        return this.name;
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
