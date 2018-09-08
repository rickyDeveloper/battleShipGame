package com.battleship.ship;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
public class BattleShip implements Ship {

    private int width;

    private int height;

    private ShipType type;

    public BattleShip(int width, int height, ShipType type) {
        this.width = width;
        this.height = height;
        this.type = type;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public ShipType getType() {
        return null;
    }
}
