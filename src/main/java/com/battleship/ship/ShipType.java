package com.battleship.ship;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
public enum ShipType {
    P(1),
    Q(2);

    private int destroyHitCounts;

    ShipType(int destroyHitCounts) {
        this.destroyHitCounts = destroyHitCounts;
    }

    public int getDestroyHitCounts() {
        return this.destroyHitCounts;
    }

}
