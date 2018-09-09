package com.battleship.engine;

import com.battleship.coordinate.Coordinate;
import com.battleship.player.Person;
import com.battleship.player.Player;
import com.battleship.ship.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * We can make this class as SmartLifeCycle also.
 * 
 * Created by vikasnaiyar on 09/09/18.
 */
@Component
@Qualifier("shipBattleEngine")
public class ShipBattleEngine implements BattleEngine {

    private Player player1;

    private  Player player2;

    private boolean isPlayer1MissileSender;

    private boolean isGameOver;

    @Autowired
    public ShipBattleEngine(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.isPlayer1MissileSender = true;
        this.isGameOver = false;
    }

    @Override
    public void startGame() {
        System.out.println("Starting the battle...");
        while(canGameContinue()) {
            boolean hasMissileHit = false;
            Player missileSender = getMissileSender();
            if(missileSender.hasNextMissile()) { //if next missile is available
                Coordinate coordinate = missileSender.getNextMissileTarget();
                Player missileReciever = getMissileReceiver();
                hasMissileHit = missileReciever.hasMissileHitTarget(coordinate);
                System.out.printf("%s fires a missile with target %s which got %s\n", missileSender.getName(), coordinate, (hasMissileHit ? "hit" : "miss" ));
                if(hasMissileHit && missileReciever.areAllShipsDestroyed()) { // is missile was hit then update game status
                    this.isGameOver = true;
                    System.out.printf("%s won the battle\n", missileSender.getName());
                }
            } else {
                System.out.printf("%s has not more missiles left to launch\n", missileSender.getName());
            }

            if(!this.isGameOver) { // update next missile sender
                updateMissileSender(hasMissileHit);
            }
        }

        if(!isGameOver) { // Once the players stop and we have a drawn match
            System.out.println("Battle ended in a draw");
        }

        System.out.println("Closing the battle...good bye!!");
    }


    private boolean canGameContinue() {
        return !isGameOver && (player1.hasNextMissile() || player2.hasNextMissile());
    }

    private void updateMissileSender(boolean hasMissileHit) {
        isPlayer1MissileSender = (hasMissileHit ? isPlayer1MissileSender : !isPlayer1MissileSender);
    }


    private Player getMissileSender() {
        return   ( isPlayer1MissileSender ? player1 : player2) ;
    }


    private Player getMissileReceiver(){
        return  isPlayer1MissileSender ? player2 : player1;
    }

    /**
     * Will implement if required
     * @return
     */
    @Override
    public boolean endGame() {
        return false;
    }

    /**
     * Will implement if required
     * @return
     */
    @Override
    public boolean pauseGame() {
        return false;
    }
}
