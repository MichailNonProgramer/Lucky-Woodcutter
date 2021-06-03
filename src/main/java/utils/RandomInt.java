package utils;

public class RandomInt {
    public static int getValue(int max, int min) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
