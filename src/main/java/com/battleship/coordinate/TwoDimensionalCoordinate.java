package com.battleship.coordinate;

import com.battleship.coordinate.Coordinate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * This class can be made generic also by parameterizing TwoDimensionalCoordinate<X,Y>
 *
 * At state of  TwoDimensionalCoordinate is not getting
 * changed I am re-using it for same both the palyers.
 *
 * i.e same coordinates for both players
 *
 * Created by vikasnaiyar on 08/09/18.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TwoDimensionalCoordinate implements Coordinate {

    private int x;

    private char y;

    private  boolean isOccupied = false;

    public TwoDimensionalCoordinate(char y, int x) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public char getY() {
        return y;
    }


    @Override
    public String toString(){
        return  "" + y + x;
    }


    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Overriding as using these coordinates in hashset
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof TwoDimensionalCoordinate)) {
            return false;
        }

        TwoDimensionalCoordinate other = (TwoDimensionalCoordinate) o;

        return this.x == other.x &&
                this.y == other.y ;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.x;
        result = 31 * result + ((int) this.y);
        return result;
    }
}
