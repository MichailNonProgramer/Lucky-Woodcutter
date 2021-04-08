package utils;

import java.util.List;

public class Key {
    public int presses, releases;
    public boolean down, clicked;

    public Key(List<Key> keys) {
        keys.add(this);
    }

    public void toggle(boolean pressed) {
        if (pressed != down) {
            down = pressed;
        }
        if (pressed) {
            presses++;
        }
    }

    public void tick() {
        if (releases < presses) {
            releases++;
            clicked = true;
        } else {
            clicked = false;
        }
    }
}