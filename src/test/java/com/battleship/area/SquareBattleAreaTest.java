package com.battleship.area;

import com.battleship.com.battleship.coordinate.CoordinateTestConfig;
import com.battleship.coordinate.Coordinate;
import com.battleship.coordinate.TwoDimensionalCoordinate;
import com.battleship.ship.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

/**
 * Created by vikasnaiyar on 08/09/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SquareBattleArea.class)
@Import({ SquareBattleAreaTestConfig.class, CoordinateTestConfig.class })
public class SquareBattleAreaTest {

    @Autowired
    private BattleArea squareBattleArea;

    @Mock
    private Ship ship;

    @Mock
    private TwoDimensionalCoordinate coordinate;


    @Before
    public void doSetUp() {
        Assert.assertNotNull("BattleArea object should not be null", squareBattleArea);
        Mockito.when(ship.getHeight()).thenReturn(2);
        Mockito.when(ship.getWidth()).thenReturn(2);
        Mockito.when(coordinate.getX()).thenReturn(1);
        Mockito.when(coordinate.getY()).thenReturn('A');
    }

    @Test
    public void testBattleAreaSize() {
        Assert.assertEquals("BattleArea units should be equal", 9, squareBattleArea.getBattleArea()) ;
    }

    @Test
    public void testIncorrectBattleAreaSize() {
        Assert.assertNotEquals("BattleArea units should not be equal" , 11, squareBattleArea.getBattleArea()) ;
    }

    @Test
    public void testParkShip(){
        Collection<Coordinate> allocatedCoordinate = squareBattleArea.parkShip(ship,coordinate);
        Assert.assertNotEquals("Coordinates Allocated count cannot be zero" , 0, allocatedCoordinate.size());

        boolean foundMatchingCoordinate = allocatedCoordinate.stream()
                .anyMatch(c -> ((TwoDimensionalCoordinate)c).getX() == coordinate.getX() && ((TwoDimensionalCoordinate)c).getY() == coordinate.getY());
        Assert.assertTrue("Expected coordinate not found", foundMatchingCoordinate);

        foundMatchingCoordinate = allocatedCoordinate.stream()
                .anyMatch(c -> ((TwoDimensionalCoordinate)c).getX() == 20 && ((TwoDimensionalCoordinate)c).getY() == 'Z');
        Assert.assertFalse("UnExpected coordinate found", foundMatchingCoordinate);

    }


}
