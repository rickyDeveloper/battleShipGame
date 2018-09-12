package com.battleship.app;

import com.battleship.area.BattleArea;
import com.battleship.engine.BattleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * Main class for this app which starts the game
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

        BattleEngine battleEngine = (BattleEngine) appContext.getBean("shipBattleEngine");

        battleEngine.startGame();
    }

}
