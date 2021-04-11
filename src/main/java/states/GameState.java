package states;

import states.StateManager;

import java.awt.*;

public abstract class GameState {

    private StateManager gameStateManager;

    public GameState(StateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public abstract void update();

//    public abstract void input(MouseHandler mouse, KeyboardHandler keyboard);

    public abstract void render(Graphics2D graphics2D);

}