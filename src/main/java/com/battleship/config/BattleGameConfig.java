package com.battleship.config;


import com.battleship.area.BattleArea;
import com.battleship.coordinate.Coordinate;
import com.battleship.coordinate.DestroyableCoordinate;
import com.battleship.coordinate.ShipCoordinate;
import com.battleship.coordinate.TwoDimensionalCoordinate;
import com.battleship.player.BattleShipGamePlayer;
import com.battleship.player.Person;
import com.battleship.player.Player;
import com.battleship.ship.BattleShip;
import com.battleship.ship.Ship;
import com.battleship.ship.ShipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Configuration for square battle area
 * Created by vikasnaiyar on 09/09/18.
 */
@Configuration
@Import({SquareBattleAreaConfig.class, PersonConfig.class})
public class BattleGameConfig {

    @Value("${battlearea.ships.count}")
    private int shipCount;

    @Value("#{'${battlearea.ships.details}'.split(',')}")
    private String[] shipDetails;

    @Value("${battlearea.player1.missile.targets}")
    private String player1MissileTargets;

    @Value("${battlearea.player2.missile.targets}")
    private String player2MissileTargets;

    @Autowired
    private BattleArea battleArea;

    /**
     * Beans for player 1
     *
     * @param person1
     * @return
     */
    @Bean
    @Autowired
    public Player player1(Person person1) {
        return new BattleShipGamePlayer(person1, getPlayer1ShipCoordinates(), getPlayer1MissileTargets());
    }

    /**
     * Bean for player 2
     *
     * @param person2
     * @return
     */
    @Bean
    @Autowired
    public Player player2(Person person2) {
        return new BattleShipGamePlayer(person2, getPlayer2ShipCoordinates(), getPlayer2MissileTargets());
    }

    /**
     * Reading the properties file and setting up the player
     * <p>
     * For simplicity and less complexity I have repeated
     * the logic.
     * <p>
     * If we want to improve the performance then improve on below logic
     *
     * @return
     */
    public Map<Ship, Collection<DestroyableCoordinate>> getPlayer1ShipCoordinates() {

        Map<Ship, Collection<DestroyableCoordinate>> playerShipCoordinatesMap = new HashMap<>();

        IntStream.range(0, shipCount).forEach(index -> {
            String[] shipInfo = shipDetails[index].trim().split(" ");

            Ship ship = getBattleShip(Integer.valueOf(shipInfo[1]), Integer.valueOf(shipInfo[2]), ShipType.getShipType(shipInfo[0]));

            Coordinate playerStartCoordinate = new TwoDimensionalCoordinate(shipInfo[3].charAt(0), Integer.valueOf(shipInfo[3].substring(1)));

            playerShipCoordinatesMap.putIfAbsent(ship, getCoordinatesForShip(ship, playerStartCoordinate));
        });

        return playerShipCoordinatesMap;
    }

    /**
     * Same coments as above
     *
     * @return
     */
    public Map<Ship, Collection<DestroyableCoordinate>> getPlayer2ShipCoordinates() {

        Map<Ship, Collection<DestroyableCoordinate>> playerShipCoordinatesMap = new HashMap<>();

        IntStream.range(0, shipCount).forEach(index -> {
            String[] shipInfo = shipDetails[index].trim().split(" ");

            Ship ship = getBattleShip(Integer.valueOf(shipInfo[1]), Integer.valueOf(shipInfo[2]), ShipType.getShipType(shipInfo[0]));

            Coordinate playerStartCoordinate = getTwoDimensionalCoordinate(shipInfo[4].charAt(0), Integer.valueOf(shipInfo[4].substring(1)));

            playerShipCoordinatesMap.putIfAbsent(ship, getCoordinatesForShip(ship, playerStartCoordinate));
        });


        return playerShipCoordinatesMap;
    }


    /**
     * These are details of missile target for player
     *
     * @return
     */
    public List<Coordinate> getPlayer1MissileTargets() {

        String[] missileTargets = player1MissileTargets.trim().split(" ");

        return getMissileCoordinates(missileTargets);
    }


    /**
     * These are details of missile target for player
     *
     * @return
     */
    public List<Coordinate> getPlayer2MissileTargets() {

        String[] missileTargets = player2MissileTargets.trim().split(" ");

        return getMissileCoordinates(missileTargets);
    }


    private List<Coordinate> getMissileCoordinates(String[] missileTargets) {

        List<Coordinate> missileTargetCoordinates = new ArrayList<>();

        for (String missileTarget : missileTargets) {
            Coordinate missileTargetCoordinate = getTwoDimensionalCoordinate(missileTarget.trim().charAt(0), Integer.valueOf(missileTarget.trim().substring(1)));
            missileTargetCoordinates.add(missileTargetCoordinate);
        }

        return missileTargetCoordinates;

    }

    /**
     * The method tries to park the ship starting from a specific position
     *
     * @param ship
     * @param coordinate
     * @return
     */
    private Collection<DestroyableCoordinate> getCoordinatesForShip(Ship ship, Coordinate coordinate) {
        Collection<Coordinate> allocatedCoordinate = battleArea.placeShip(ship, coordinate);
        return allocatedCoordinate.stream().map(ac ->
                getShipCoordinate(ship.getType().getDestroyHitCounts(), ac)
        ).collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Factory methods for prototype beans
     *
     * @param width
     * @param height
     * @param type
     * @return
     */
    @Lookup
    public BattleShip getBattleShip(int width, int height, ShipType type) {
        return null;
    }

    @Lookup
    public TwoDimensionalCoordinate getTwoDimensionalCoordinate(char y, int x) {
        return null;
    }

    @Lookup
    public ShipCoordinate getShipCoordinate(int destroyHitCount, Coordinate coordinate) {
        return null;
    }

    @Lookup
    public Player getBattleShipGamePlayer(Person person, Map<Ship, Collection<DestroyableCoordinate>> shipCoordinatesMap, List<Coordinate> missileTargets) {
        return null;
    }
}