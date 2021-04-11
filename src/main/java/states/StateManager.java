package states;

import java.awt.*;
import java.util.ArrayList;

public class StateManager {
    private ArrayList<GameState> states;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;


    public StateManager() {
        states = new ArrayList<GameState>();
        //states.add(new PlayState(this));
    }

    public void addState(int state) {
//        switch (state) {
//            case PLAY:
//                states.add(new PlayState(this));
//                break;
//            case MENU:
//                states.add(new MenuState(this));
//                break;
//            case PAUSE:
//                states.add(new PauseState(this));
//                break;
//            case GAMEOVER:
//                states.add(new GameOverState(this));
//                break;
//        }
    }

    public void pop(int state) {
        states.remove(state);
    }

    public void changeState(int state) {
        states.remove(0);
        addState(state);
    }

    public void update() {
       // Vector.setWorldVectorCords(map.getX(), map.getY());
        for (GameState state : states) {
            state.update();
        }
    }

//    public void input(MouseHandler mouse, KeyboardHandler keyboard) {
//        for (GameState state : states) {
//            state.input(mouse, keyboard);
//        }
//    }

    public void render(Graphics2D graphics2D) {
        for (GameState state : states) {
            state.render(graphics2D);
        }
    }
}
