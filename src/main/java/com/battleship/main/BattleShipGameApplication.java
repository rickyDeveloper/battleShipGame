package com.battleship.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by vikasnaiyar on 08/09/18.
 */
@SpringBootApplication
public class BattleShipGameApplication implements CommandLineRunner {

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
        if (args.length > 0) {
            System.out.println("Arguments passed");
        } else {
            System.out.println(" No Arguments passed");
        }

    }
}
