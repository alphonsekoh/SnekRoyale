package com.mygdx.game.gamestates;

import com.mygdx.game.managers.StateManage;

public abstract class GameState {
    final StateManage stateManager;

    GameState(StateManage stateManager) {
        this.stateManager = stateManager;
        setup();
    }

    protected abstract void setup();

    public abstract void update(float time);

    public abstract void paint();

    public abstract void inputKeyHandle();

    public abstract void dispose();

}
