package config;

public class GameConfig {
    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    private static final int screenWidth = 1470;
    private static final int screenHeight = 910;

    public static int getMapWidth() {
        return mapWidth;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    private static final int mapWidth = 10500;
    private static final int mapHeight = 10500;
}