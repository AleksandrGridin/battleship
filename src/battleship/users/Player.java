package battleship.users;

import battleship.Console;
import battleship.ships.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private final String[][] field = new String[11][11];
    private final String[][] fok = new String[11][11];
    private List<Ship> listShips = new ArrayList<>();
    private boolean win = false;
    private Player player;

    public List<Ship> getListShips() {
        return listShips;
    }

    public boolean isWin() {
        return win;
    }

    public String[][] getField() {
        return field;
    }

    public String[][] getFok() {
        return fok;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void initGame(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (i == 0 && j == 0) {
                    field[i][j] = " ";
                    continue;
                }
                if (i == 0) {
                    field[i][j] = String.valueOf(j);
                    continue;
                }
                if (j == 0) {
                    char ch = (char) (64 + i);
                    field[i][j] = String.valueOf(ch);
                    continue;
                }
                field[i][j] = "~";
            }
        }
    }


    public void printField(String[][] field) {
        for (String[] horis : field) {
            for (String cell : horis ) {
                System.out.print(cell);
                System.out.print(' ');
            }
            System.out.println();
        }
    }


    public void locateShips() {
        printField(field);
        System.out.println();
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):\n");
        while (true) {
            String coordinates = checkData(Console.read());
            AircraftCarrier ac = new AircraftCarrier();
            if (checkCoordinate(coordinates, ac)) {
                addShip(coordinates, ac);
                listShips.add(ac);
                printField(field);
                System.out.println();
                break;
            }
        }
        System.out.println("Enter the coordinates of the Battleship (4 cells):\n");
        while (true) {
            String coordinates = checkData(Console.read());
            Battleship bs = new Battleship();
            if (checkCoordinate(coordinates, bs)) {
                addShip(coordinates, bs);
                listShips.add(bs);
                printField(field);
                System.out.println();
                break;
            }
        }
        System.out.println("Enter the coordinates of the Submarine (3 cells):\n");
        while (true) {
            String coordinates = checkData(Console.read());
            Submarine submarine = new Submarine();
            if (checkCoordinate(coordinates, submarine)) {
                addShip(coordinates, submarine);
                listShips.add(submarine);
                printField(field);
                System.out.println();
                break;
            }
        }
        System.out.println("Enter the coordinates of the Cruiser (3 cells):\n");
        while (true) {
            String coordinates = checkData(Console.read());
            Cruiser cruiser = new Cruiser();
            if (checkCoordinate(coordinates, cruiser)) {
                addShip(coordinates, cruiser);
                listShips.add(cruiser);
                printField(field);
                System.out.println();
                break;
            }
        }
        System.out.println("Enter the coordinates of the Destroyer (3 cells):\n");
        while (true) {
            String coordinates = checkData(Console.read());
            Destroyer destroyer = new Destroyer();
            if (checkCoordinate(coordinates, destroyer)) {
                addShip(coordinates, destroyer);
                listShips.add(destroyer);
                printField(field);
                System.out.println();
                break;
            }
        }
    }


    private boolean checkCoordinate(String coordinates, Ship ship) {
        String[] line = coordinates.split(" ");
        String[] firstCoordinate = line[0].split("", 2);
        String[] secondCoordinate = line[1].split("", 2);

        int hf = firstCoordinate[0].charAt(0) - 64;
        int hs = secondCoordinate[0].charAt(0) - 64;

        int firstElement = Integer.parseInt(firstCoordinate[1]);
        int secondElement = Integer.parseInt(secondCoordinate[1]);



        int lengthOfShip =  firstElement == secondElement
                ? hs - hf + 1 : secondElement - firstElement + 1;

        if (firstCoordinate[0].equals(secondCoordinate[0]) ||
                firstCoordinate[1].equals(secondCoordinate[1])) {
        } else {
            System.out.println("Error! Wrong ship location! Try again:\n");
            return false;
        }
        if (ship.getCells() != lengthOfShip) {
            System.out.println("Error! Wrong length of the " + ship.getName() + "! Try again:\n");
            return false;
        }

        for (int i = hf > 1 ? hf - 1 : hf; i <= (hs < 10 ? hs + 1 : hs); i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (Arrays.asList(Arrays.copyOfRange(field[i],
                        firstElement > 1 ? firstElement - 1 : firstElement,
                        secondElement > 1 ? secondElement + 1 : secondElement)).contains("O")) {
                    System.out.println("Error! You placed it too close to another one. Try again:\n");
                    return false;
                }
            }
        }
        return true;
    }


    private void addShip(String coordinates, Ship ship) {
        String[] line = coordinates.split(" ");
        String[] firstCoordinate = line[0].split("", 2);
        String[] secondCoordinate = line[1].split("", 2);


        int firstElement = Integer.parseInt(firstCoordinate[1]);
        int secondElement = Integer.parseInt(secondCoordinate[1]);
        int hf = firstCoordinate[0].charAt(0) - 64;
        int hs = secondCoordinate[0].charAt(0) - 64;


        for (int i = hf; i <= hs; i++) {
            for (int j = firstElement; j <= secondElement; j++) {
                field[i][j] = "O";
                ship.addSell(i, j);
            }
        }
    }


    private String checkData(String console) {
        String[] line = console.split(" ");
        String[] firstCoordinate = line[0].split("", 2);
        String[] secondCoordinate = line[1].split("", 2);

        int hf = firstCoordinate[0].charAt(0) - 64;
        int hs = secondCoordinate[0].charAt(0) - 64;

        int firstElement = Integer.parseInt(firstCoordinate[1]);
        int secondElement = Integer.parseInt(secondCoordinate[1]);
        System.out.println();
        if (hs == hf) {
            if (secondElement >= firstElement) {
                return line[0] + " " + line[1];
            } else {
                return line[1] + " " + line[0];
            }
        }
        if (firstElement == secondElement) {
            if (hs >= hf) {
                return line[0] + " " + line[1];
            } else {
                return line[1] + " " + line[0];
            }
        }

        return line[0] + " " + line[1];
    }


    public void takeAHit() {
        while (true) {
            String fromConsole = Console.read();
            if (fromConsole.isEmpty()) {
                continue;
            }
            String[] line = fromConsole.split("", 2);
            System.out.println();
            int letter = line[0].charAt(0) - 64;
            int digit = Integer.parseInt(line[1]);
            if (line[0].charAt(0) > 'J') {
                System.out.println("Error! You entered the wrong coordinates! Try again:\n");
                continue;
            }
            if (digit > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:\n");
                continue;
            }
            if (player.field[letter][digit].equals("O") || player.field[letter][digit].equals("X")) {
                player.field[letter][digit] = "X";
            //    fok[letter][digit] = "X";
                printField(getFok());
                System.out.println("---------------------");
                printField(player.field);
                for (Ship ship : player.getListShips()) {
                    ship.getAHit(letter, digit);
                }
                if (player.getListShips().removeIf(Ship::isDead)) {
                    if (!player.listShips.isEmpty()) {
                        System.out.println();
                        System.out.println("You sank a ship!\n");
                    }

                }
                if (player.getListShips().isEmpty()) {
                    win = true;
                    break;
                }
                break;
            } else if (player.field[letter][digit].equals("~") || player.field[letter][digit].equals("M")) {
                player.field[letter][digit] = "M";
            //    fok[letter][digit] = "M";
                printField(getFok());
                System.out.println("---------------------");
                printField(player.field);
                System.out.println();
                System.out.println("You missed!\n");
                break;
            }
        }
    }
}
