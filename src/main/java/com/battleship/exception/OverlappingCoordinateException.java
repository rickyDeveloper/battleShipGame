package com.battleship.exception;

/**
 * User defined exception for scenarios when we observe overlapping coordinates for parking
 * Created by vikasnaiyar on 12/09/18.
 */
public class OverlappingCoordinateException extends Exception {

    public OverlappingCoordinateException(String message) {
        super(message);
    }
}
