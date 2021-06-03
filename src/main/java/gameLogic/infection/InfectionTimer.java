package gameLogic.infection;

import config.GameConfig;
import map.GameMap;

import java.util.Timer;
import java.util.TimerTask;

public class InfectionTimer {
    public static void start(GameMap gameMap) {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                new InfectionHandler(gameMap).infect();
            }
        };
        Timer timer = new Timer("InfectionTimer");

        long delay = 1000L * GameConfig.getInfectionPeriod();
        long period = 1000L * GameConfig.getInfectionPeriod();
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }
}