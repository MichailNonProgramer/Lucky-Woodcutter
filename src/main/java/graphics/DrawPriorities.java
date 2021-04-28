package graphics;

public enum DrawPriorities {
    PLAYER (2),
    GROUND (10),
    Tree(9),
    Wall(9),
    // здесь дальше под все объекты
    ;

    public int getValue() {
        return priority;
    }

    private final int priority;

    DrawPriorities(int priority) {
        this.priority = priority;
    }
}
