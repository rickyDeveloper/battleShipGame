package com.battleship.engine;

import com.battleship.area.SquareBattleArea;
import com.battleship.area.SquareBattleAreaTestConfig;
import com.battleship.com.battleship.coordinate.CoordinateTestConfig;
import com.battleship.player.BattleShipGamePlayer;
import com.battleship.player.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShipBattleEngine.class)
@Import({ ShipBattleEngineTestConfig.class })
@Ignore
public class ShipBattleEngineTest {

    @Autowired
    private Player player1;

    @Autowired
    private Player player2;

    @Autowired
    private Player player1Draw;

    @Autowired
    private Player player2Draw;


    @Test
    public void testPlayer1Winning() {
   /*     BattleEngine shipBattleEngine = new ShipBattleEngine(player1, player2);
        shipBattleEngine.startGame();
        Assert.assertEquals("Player1 should have won", player1.getName(), ((ShipBattleEngine)shipBattleEngine).getMissileSender().getName());
   */ }

    @Test
    public void testPlayer2Winning() {
    /*    BattleEngine shipBattleEngine = new ShipBattleEngine(player2, player1);
        shipBattleEngine.startGame();
        Assert.assertEquals("Player1 should have won", player1.getName(), ((ShipBattleEngine)shipBattleEngine).getMissileSender().getName());
    */}


}


