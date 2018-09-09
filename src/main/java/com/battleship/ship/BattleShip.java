package com.battleship.ship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BattleShip implements Ship {

    private int width;

    private int height;

    private ShipType type;

    @Autowired
    public BattleShip(int width, int height, ShipType type) {
        this.width = width;
        this.height = height;
        this.type = type;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public ShipType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof BattleShip)) {
            return false;
        }

        BattleShip other = (BattleShip) obj;

        return this.width == other.width &&
                this.height == other.height &&
                this.type == other.type;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.width;
        result = 31 * result + this.height;
        result = 31 * result + (type != null ? type.name().hashCode() : 17);
        return result;
    }
}
