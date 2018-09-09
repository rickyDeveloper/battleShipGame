package com.battleship.engine;

/**
 * Created by vikasnaiyar on 09/09/18.
 */
public interface BattleEngine {

    /**
     * In this impl we are using this method
     */
    void startGame();

    /**
     * Below methods are just add-on. Not being used in current impl
     * @return
     */
    boolean endGame();

    boolean pauseGame();

    boolean isGameOver();

}
