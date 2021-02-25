package com.mygdx.game.gamestates;

import com.mygdx.game.managers.StateManage;

public abstract class GameState implements States{
    final StateManage stateManager;

    /**
     * Constructor to set the state of the game
     * @param stateManager
     */
    GameState(StateManage stateManager) {
        this.stateManager = stateManager;
        setup();
    }

    protected abstract void setup();

}
