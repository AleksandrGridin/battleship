package battleship.ships;

public abstract class Ship {

    protected String name;
    protected int cells;

    public String getName() {
        return name;
    }

    public int getCells() {
        return cells;
    }
}
