package battleship;

import battleship.ships.*;

import java.util.Arrays;

public class Main {

    private final String[][] field = new String[11][11];

    public static void main(String[] args) {
        Main main = new Main();
        main.initGame();
        main.printField();
        main.locateShips();
    }

    private void initGame() {
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

    private void printField() {
        for (String[] horis : field) {
            for (String cell : horis ) {
                System.out.print(cell);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    }

    private void locateShips() {
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):\n");
        while (true) {
            String coordinates = Console.read();
            AircraftCarrier ac = new AircraftCarrier();
            if (checkCoordinate(coordinates, ac)) {
                addShip(coordinates, ac);
                printField();
                break;
            }
            System.out.println();
        }
        System.out.println("Enter the coordinates of the Battleship (4 cells):\n");
        while (true) {
            String coordinates = Console.read();
            Battleship bs = new Battleship();
            if (checkCoordinate(coordinates, bs)) {
                addShip(coordinates, bs);
                printField();
                break;
            }
            System.out.println();
        }
        System.out.println("Enter the coordinates of the Submarine (3 cells):\n");
        while (true) {
            String coordinates = Console.read();
            Submarine submarine = new Submarine();
            if (checkCoordinate(coordinates, submarine)) {
                addShip(coordinates, submarine);
                printField();
                break;
            }
        }
        System.out.println("Enter the coordinates of the Cruiser (3 cells):\n");
        while (true) {
            String coordinates = Console.read();
            Cruiser cruiser = new Cruiser();
            if (checkCoordinate(coordinates, cruiser)) {
                addShip(coordinates, cruiser);
                printField();
                break;
            }
        }
        System.out.println("Enter the coordinates of the Destroyer (3 cells):\n");
        while (true) {
            String coordinates = Console.read();
            Destroyer destroyer = new Destroyer();
            if (checkCoordinate(coordinates, destroyer)) {
                addShip(coordinates, destroyer);
                printField();
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
            System.out.println("Error! Wrong length of the Submarine! Try again:\n");
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
            }
        }
    }
}
