package com.battleship.area;

import com.battleship.com.battleship.coordinate.CoordinateTestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vikasnaiyar on 08/09/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SquareBattleArea.class)
@Import({ SquareBattleAreaTestConfig.class, CoordinateTestConfig.class })
public class SquareBattleAreaTest {

    @Autowired
    private BattleArea squareBattleArea;

    @Before
    public void doSetUp() {
        Assert.assertNotNull("BattleArea object should not be null", squareBattleArea);
    }

    @Test
    public void testBattleAreaSize() {
        Assert.assertEquals("BattleArea units should be equal", 9, squareBattleArea.getBattleArea()) ;
    }
}
