# battleShipGame

This is a POC app for building a battle ship game.

App is build using Spring boot, Java 8 and Gradle 4.10

## Steps to run this app

P.S:- Instead of command line input args, I am using application.properties to provide command line args.

1)  Go to root directory of app
2) ./gradlew build && java -jar build/libs/battleship-game-1.0-SNAPSHOT.jar

  OR

3) gradle build java && -jar build/libs/battleship-game-1.0-SNAPSHOT.jar

4) gradle test will run the tests

5) Pls add Input arguments(test cases) in application.properties present in src/main/resources folder

Spring boot Main class of the application is com.battleship.app.BattleShipGameApplication

## Design & OOPs

1) Defined BattleArea as interface and provided implementation of SquareBattleArea.
   In future we may have battle area of other shapes also.

2) BattleArea is repesented a two dimensional array of Coordinates.
   In general coordinates could be of n-dimension hence I have kept the Coordinate interface as marker interface

3) TwoDimensionalCoordinate is concrete impl for Coordinate which represent two dimension unit.

4) DestroyableCoordinate is a specific coordinate which can be destroyed. ( just for gaming purpose)
   Tried to use a sort of decorator pattern for ShipCoordinate.

5) ShipCoordinate is a wrapper around  Coordinate which has additional features.
   Ex: it can be hit and destroyed.

6) Person is simple POJO

7) Player is sort of facade which has multiple feature. Player not only has details of person who is playing.
   But it has details of coordinates at which his ships are parked.
   BattleShipGamePlayer is the concrete class for this infrom Sort a big class now but has scope of refactoring.
   Player had knowledge to decide whether his ship was it or not.

8) Ship is a simple interface to represent ship.
   BattleShip provide concrete impl
   ShipType is an enum for types of ships.

9) BattleEngine is an interface with ability to start/stop/pause game
   ShipBattleEngine is the most important class for this app.
   It has details of both the players and game playing logic.
   There is scope to refactor this class and move the game playing logic outside.










x`




