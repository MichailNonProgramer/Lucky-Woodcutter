package config;

public class Config {
    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    private static final int screenWidth = 1450;
    private static final int screenHeight = 850;

    public static int getMapWidth() {
        return mapWidth;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    private static final int mapWidth = 1450;
    private static final int mapHeight = 850;
}
