package com.battleship.coordinate;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ShipCoordinate implements DestroyableCoordinate {

    private Coordinate coordinate;

    private int maxHitCount;

    private int currentHitCount;

    public ShipCoordinate(int maxHitCount, Coordinate coordinate) {
        this.maxHitCount = maxHitCount;
        this.coordinate = coordinate;
        this.currentHitCount = 0;
    }

    public int getMaxHitCount() {
        return maxHitCount;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public boolean hit(Coordinate coordinate) {
        boolean isHit = false;

        if(currentHitCount < maxHitCount && this.coordinate.equals(coordinate)) {
            ++currentHitCount;
            isHit = true;
        }

        return isHit;
    }

    @Override
    public boolean isDestroyed() {
        return currentHitCount >= maxHitCount;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof ShipCoordinate)) {
            return false;
        }

        ShipCoordinate other = (ShipCoordinate) o;

        return this.maxHitCount == other.maxHitCount &&
                this.coordinate.equals(other.coordinate);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.maxHitCount;
        result = 31 * result + this.coordinate.hashCode();
        return result;
    }
}
