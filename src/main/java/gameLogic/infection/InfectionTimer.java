package gameLogic.infection;

import map.GameMap;

import java.util.Timer;
import java.util.TimerTask;

public class InfectionTimer {
    public static void start(GameMap gameMap) {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                new InfectMap(gameMap).infect();
            }
        };
        Timer timer = new Timer("InfectionTimer");

        // 2 число - секунды
        long delay = 1000L * 10;
        long period = 1000L * 15;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }
}