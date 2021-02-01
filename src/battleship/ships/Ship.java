package battleship.ships;

import battleship.Cell;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship {

    protected String name;
    protected boolean isDead = false;
    protected int cells;
    protected List<Cell> listCells = new ArrayList<>();

    public String getName() {
        return name;
    }

    public boolean isDead() {
        return listCells.size() == 0;
    }

    public void getAHit(int i, int j) {

        if (listCells.removeIf(cell -> cell.getI() == i && cell.getJ() == j)) {
            if (!listCells.isEmpty()) {
                System.out.println();
                System.out.println("You hit a ship!\n");
            }
        }

    }

    public void addSell(int i, int j) {
        listCells.add(new Cell(i, j));
    }

    public int getCells() {
        return cells;
    }

    public List<Cell> getListCells() {
        return listCells;
    }
}
