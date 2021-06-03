package gameLogic.growing;

import config.GameConfig;
import map.GameMap;

import java.util.Timer;
import java.util.TimerTask;

public class TreesGrowTimer {
    public static void start(GameMap gameMap) {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                new TreesGrowHandler(gameMap).grow();
            }
        };
        Timer timer = new Timer("TreesGrowTimer");

        long delay = 1000L * GameConfig.getGrowPeriod();
        long period = 1000L * GameConfig.getGrowPeriod();
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }
}