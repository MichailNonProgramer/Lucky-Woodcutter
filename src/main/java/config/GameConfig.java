package config;

public class GameConfig {
    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    private static final int screenWidth = 1530;
    private static final int screenHeight = 990;

    public static int getMapWidth() {
        return mapWidth;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    private static final int mapWidth = 4500;
    private static final int mapHeight = 4500;

    public static int getGrowPeriod() {
        return growPeriod;
    }

    public static int getInfectionPeriod() {
        return infectionPeriod;
    }

    private static final int growPeriod = 60;
    private static final int infectionPeriod = 13;

    public static boolean getIsSoloGame(){ return isSoloGame;}

    private static final boolean isSoloGame = true;

}
