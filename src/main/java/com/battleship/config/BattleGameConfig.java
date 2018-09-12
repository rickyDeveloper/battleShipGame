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
import org.springframework.core.env.Environment;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Configuration for square battle area
 * Created by vikasnaiyar on 09/09/18.
 */
@Configuration
@Import({SquareBattleAreaConfig.class})
public class BattleGameConfig {

    @Autowired
    private Environment env;

    public static final String PLAYERS_MISSILE_TARGET_PROPERTY = "battlearea.missile.targets.player";

    @Value("${battlearea.ships.count}")
    private int shipCount;

    @Value("#{'${battlearea.ships.details}'.split(',')}")
    private String[] shipDetails;

    @Value("${battlearea.players.count}")
    private int playersCount;

    @Value("${battlearea.ships.details.coordinates.offset}")
    private int coordinatesOffset;


    @Bean
    public List<Player> players() {
         List<Player> players  = new ArrayList<>();

        IntStream.rangeClosed(1,playersCount).forEach( i -> {
            Player player = new BattleShipGamePlayer("Player-"+i, getPlayerShipCoordinates(i), getPlayerMissileTargets(i));
            players.add(player);
        });

         return players;
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
    public Map<Ship, Collection<DestroyableCoordinate>> getPlayerShipCoordinates(int playerNumber) {

        Map<Ship, Collection<DestroyableCoordinate>> playerShipCoordinatesMap = new HashMap<>();

        BattleArea battleArea = getBattleArea();

        int playerInputCoordinateOffset = coordinatesOffset+playerNumber;

        IntStream.range(0, shipCount).forEach(index -> {
            String[] shipInfo = shipDetails[index].trim().split(" ");

            Ship ship = getBattleShip(Integer.valueOf(shipInfo[1]), Integer.valueOf(shipInfo[2]), ShipType.getShipType(shipInfo[0]));

            Coordinate playerStartCoordinate = new TwoDimensionalCoordinate(shipInfo[playerInputCoordinateOffset].charAt(0), Integer.valueOf(shipInfo[playerInputCoordinateOffset].substring(1)));

            Collection<DestroyableCoordinate> destroyableCoordinatesForShip =  getDestroyableCoordinatesForShip(ship,
                    getCoordinatesForShip(battleArea, ship, playerStartCoordinate));

            playerShipCoordinatesMap.putIfAbsent(ship, destroyableCoordinatesForShip);
        });

        return playerShipCoordinatesMap;
    }

    /**
     * These are details of missile target for player
     *
     * @return
     */
    public List<Coordinate> getPlayerMissileTargets(int playerNumber) {

        String[] missileTargets = env.getProperty(PLAYERS_MISSILE_TARGET_PROPERTY + playerNumber).trim().split(" ");

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
     * If the ship coordinates allocation encounter overlapping coordinates, this method will through RunTimeException.
     * @param ship
     * @param coordinates
     * @return
     */
    private Collection<DestroyableCoordinate> getDestroyableCoordinatesForShip(Ship ship, Collection<Coordinate> coordinates) {
        return coordinates.stream().map(ac ->
                getShipCoordinate(ship.getType().getDestroyHitCounts(), ac)
        ).collect(Collectors.toCollection(HashSet::new));
    }

    private Collection<Coordinate> getCoordinatesForShip(BattleArea battleArea, Ship ship, Coordinate startCoordinate) {
        return battleArea.placeShip(ship, startCoordinate);
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
    public BattleArea getBattleArea() {
        return null;
    }
}