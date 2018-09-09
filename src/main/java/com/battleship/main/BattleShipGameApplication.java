package com.battleship.main;

import com.battleship.area.BattleArea;
import com.battleship.coordinate.Coordinate;
import com.battleship.engine.BattleEngine;
import com.battleship.player.Person;
import com.battleship.ship.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collection;
import java.util.Map;

/**
 * Created by vikasnaiyar on 08/09/18.
 */
@SpringBootApplication
@ComponentScan("com.battleship")
@EnableAutoConfiguration
public class BattleShipGameApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext appContext;


    public static void main(String args[]) {
        SpringApplication app = new SpringApplication(BattleShipGameApplication.class);
        app.run(args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {

        BattleArea battleArea = (BattleArea)appContext.getBean("battleArea");
        System.out.println(battleArea.getBattleArea());

        BattleEngine battleEngine = (BattleEngine) appContext.getBean("shipBattleEngine");

        battleEngine.startGame();
    }




}
