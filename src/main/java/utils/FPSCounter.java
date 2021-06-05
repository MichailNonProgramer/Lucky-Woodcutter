package utils;

final public class FPSCounter extends Thread {
    private static int startTime;
    private static int endTime;
    private static int frameTimes = 0;
    private static short frames = 0;

    public static void StartCounter() {
        startTime = (int) System.currentTimeMillis();
    }

    public static void StopAndPost() {
        endTime = (int) System.currentTimeMillis();
        frameTimes = frameTimes + endTime - startTime;
        ++frames;
        if (frameTimes >= 1000) {
            frames = 0;
            frameTimes = 0;
        }
    }
}