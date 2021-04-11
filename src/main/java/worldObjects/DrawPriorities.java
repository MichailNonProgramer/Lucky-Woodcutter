package worldObjects;

public enum DrawPriorities {
    PLAYER (5),
    // здесь дальше под все объекты
    ;

    public int getPriority() {
        return priority;
    }

    private final int priority;

    DrawPriorities(int priority) {
        this.priority = priority;
    }
}
