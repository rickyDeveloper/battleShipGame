package com.battleship.engine;

import com.battleship.coordinate.Coordinate;
import com.battleship.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * We can make this class as SmartLifeCycle also.
 * Also, we can spend more time to make startGame()
 * a bit more better.
 *
 * At present the assumption is that engine allows two palyers
 * as mentioned in document.
 * Created by vikasnaiyar on 09/09/18.
 */
@Component
@Qualifier("shipBattleEngine")
public class ShipBattleEngine implements BattleEngine {

    private List<Player> players;

    @Value("${battlearea.first.firing.player.number}")
    private int firingPlayerNumber;

    private boolean isGameOver;

    @Autowired
    public ShipBattleEngine(@Qualifier("players") List<Player> players) {
        this.players = players;
        this.isGameOver = false;
    }

    /**
     * The logic implemented is in accordance to problem statement.
     */
    @Override
    public void startGame() {
        System.out.println("Starting the battle...");
        // continue till either game gets over or both the players run out of missiles
        while(canGameContinue()) {
            boolean hasMissileHit = false;
            Player missileSender = getMissileSender();

            if(missileSender.hasNextMissile()) { //if next missile is available with sender
                Coordinate coordinate = missileSender.getNextMissileTarget();
                Player missileReciever = getMissileReceiver();
                hasMissileHit = missileReciever.hasMissileHitTarget(coordinate);

                System.out.printf("%s fires a missile with target %s which got %s\n", missileSender.getName(), coordinate, (hasMissileHit ? "hit" : "miss" ));

                if(hasMissileHit && missileReciever.areAllShipsDestroyed()) { // is missile was hit then update game status
                    this.isGameOver = true;
                    System.out.printf("%s won the battle\n", missileSender.getName());
                }

            } else { //No missile available with sender hence we need to give change to ther player
                System.out.printf("%s has not more missiles left to launch\n", missileSender.getName());
            }

            if(!this.isGameOver) { // update next missile sender if game is not over
                updateMissileSender(hasMissileHit);
            }
        }

        // This is for the case with both players ran out of missles
        if(!isGameOver) { // Once the players stop and we have a drawn match
            System.out.println("Battle ended in a draw");
        }

        System.out.println("Closing the battle...good bye!!");
    }

    private boolean canGameContinue() {
        return !isGameOver && (players.stream().anyMatch(p -> p.hasNextMissile()));
    }

    private void updateMissileSender(boolean hasMissileHit) {
        firingPlayerNumber = (hasMissileHit ? firingPlayerNumber : getNextPlayerNumber());
    }

   // @VisibleForTesting
    // This method is not private only for testing purpose.
    Player getMissileSender() {
        return players.get(firingPlayerNumber-1) ;
    }

    private Player getMissileReceiver(){
        return  players.get(getNextPlayerNumber()-1);
    }

    private int getNextPlayerNumber(){
        return (firingPlayerNumber % players.size() ) + 1;
    }
}
