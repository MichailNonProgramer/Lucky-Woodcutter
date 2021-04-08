package states;

import utils.KeyboardHandler;
import utils.MouseHandler;

import java.awt.*;

public class PlayState extends GameState {

    public PlayState(GameStateManager gameStateManager){
        super(gameStateManager);
    }
    @Override
    public void update() {

    }

    @Override
    public void input(MouseHandler mouse, KeyboardHandler keyboard) {
        if (keyboard.up.down) {
            System.out.println("W");
        }
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(100, 100, 200, 200);
        graphics2D.drawRect(100, 100, 200, 200);
    }
}
