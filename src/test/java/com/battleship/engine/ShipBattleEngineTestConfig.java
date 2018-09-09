package com.battleship.engine;

import com.battleship.coordinate.Coordinate;
import com.battleship.coordinate.TwoDimensionalCoordinate;
import com.battleship.player.BattleShipGamePlayer;
import com.battleship.player.Player;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

/**
 * Created by vikasnaiyar on 10/09/18.
 */
public class ShipBattleEngineTestConfig {

    /**
     * Creating stubs
     * @return
     */
    @Bean
    public Player player1() {
        Player player = new Player() {
            @Override
            public String getName() {
                return "Player-1";
            }

            @Override
            public boolean hasMissileHitTarget(Coordinate coordinate) {
                return false;
            }

            @Override
            public boolean hasNextMissile() {
                return true;
            }

            @Override
            public Coordinate getNextMissileTarget() {
                TwoDimensionalCoordinate coordinate = mock(TwoDimensionalCoordinate.class);
                when(coordinate.getX()).thenReturn(1);
                when(coordinate.getY()).thenReturn('A');
                return coordinate;
            }

            @Override
            public boolean areAllShipsDestroyed() {
                return false;
            }
        };

        return player;
    }

    @Bean
    public Player player2() {
        Player player = new Player() {
            @Override
            public String getName() {
                return "Player-2";
            }

            @Override
            public boolean hasMissileHitTarget(Coordinate coordinate) {
                return true;
            }

            @Override
            public boolean hasNextMissile() {
                return true;
            }

            @Override
            public Coordinate getNextMissileTarget() {
                TwoDimensionalCoordinate coordinate = mock(TwoDimensionalCoordinate.class);
                when(coordinate.getX()).thenReturn(4);
                when(coordinate.getY()).thenReturn('D');
                return coordinate;
            }

            @Override
            public boolean areAllShipsDestroyed() {
                return true;
            }
        };

        return player;
    }


    /**
     * Creating stubs
     * @return
     */
    @Bean
    public Player player2Draw() {
        Player player = new Player() {
            int coordinateCount = 4;

            @Override
            public String getName() {
                return "Player-2";
            }

            @Override
            public boolean hasMissileHitTarget(Coordinate coordinate) {
                return false;
            }

            @Override
            public boolean hasNextMissile() {
                return  coordinateCount-- > 0;
            }

            @Override
            public Coordinate getNextMissileTarget() {
                TwoDimensionalCoordinate coordinate = mock(TwoDimensionalCoordinate.class);
                when(coordinate.getX()).thenReturn(1);
                when(coordinate.getY()).thenReturn('X');
                return coordinate;
            }

            @Override
            public boolean areAllShipsDestroyed() {
                return false;
            }
        };

        return player;
    }

    @Bean
    public Player player1Draw() {
        Player player = new Player() {
            int coordinateCount = 2;

            @Override
            public String getName() {
                return "Player-1";
            }

            @Override
            public boolean hasMissileHitTarget(Coordinate coordinate) {
                return false;
            }

            @Override
            public boolean hasNextMissile() {
                return coordinateCount-- > 0;
            }

            @Override
            public Coordinate getNextMissileTarget() {
                TwoDimensionalCoordinate coordinate = mock(TwoDimensionalCoordinate.class);
                when(coordinate.getX()).thenReturn(4);
                when(coordinate.getY()).thenReturn('Z');
                return coordinate;
            }

            @Override
            public boolean areAllShipsDestroyed() {
                return false;
            }
        };

        return player;
    }


}
