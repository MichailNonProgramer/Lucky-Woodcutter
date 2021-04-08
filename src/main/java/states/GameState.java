package states;

import utils.KeyboardHandler;
import utils.MouseHandler;

import java.awt.*;

public abstract class GameState {

    private GameStateManager gameStateManager;

    public GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public abstract void update();

    public abstract void input(MouseHandler mouse, KeyboardHandler keyboard);

    public abstract void render(Graphics2D graphics2D);

}
