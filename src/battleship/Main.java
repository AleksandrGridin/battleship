package battleship;

import battleship.ships.*;

import java.util.Arrays;

public class Main {

    private static final String[][] field = new String[11][11];
    private static final String[][] fok = new String[11][11];

    public static void main(String[] args) {
        Main main = new Main();
        main.initGame(field);
        main.initGame(fok);
        main.printField(field);
        main.locateShips();
        System.out.println("The game starts!\n");
        main.printField(fok);
        main.takeAHit();
    }

    private void initGame(String[][] field) {
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

    private void printField(String[][] field) {
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
            String coordinates = checkData(Console.read());
            AircraftCarrier ac = new AircraftCarrier();
            if (checkCoordinate(coordinates, ac)) {
                addShip(coordinates, ac);
                printField(field);
                break;
            }
        }
        System.out.println("Enter the coordinates of the Battleship (4 cells):\n");
        while (true) {
            String coordinates = checkData(Console.read());
            Battleship bs = new Battleship();
            if (checkCoordinate(coordinates, bs)) {
                addShip(coordinates, bs);
                printField(field);
                break;
            }
        }
        System.out.println("Enter the coordinates of the Submarine (3 cells):\n");
        while (true) {
            String coordinates = checkData(Console.read());
            Submarine submarine = new Submarine();
            if (checkCoordinate(coordinates, submarine)) {
                addShip(coordinates, submarine);
                printField(field);
                break;
            }
        }
        System.out.println("Enter the coordinates of the Cruiser (3 cells):\n");
        while (true) {
            String coordinates = checkData(Console.read());
            Cruiser cruiser = new Cruiser();
            if (checkCoordinate(coordinates, cruiser)) {
                addShip(coordinates, cruiser);
                printField(field);
                break;
            }
        }
        System.out.println("Enter the coordinates of the Destroyer (3 cells):\n");
        while (true) {
            String coordinates = checkData(Console.read());
            Destroyer destroyer = new Destroyer();
            if (checkCoordinate(coordinates, destroyer)) {
                addShip(coordinates, destroyer);
                printField(field);
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

    private void takeAHit() {
        System.out.println("Take a shot!\n");
        while (true) {
            String[] line = Console.read().split("", 2);
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
            if (field[letter][digit].equals("O")) {
                field[letter][digit] = "X";
                fok[letter][digit] = "X";
                printField(fok);
                System.out.println("You hit a ship!\n");
                printField(field);
                break;
            } else if (field[letter][digit].equals("~")) {
                field[letter][digit] = "M";
                fok[letter][digit] = "M";
                printField(fok);
                System.out.println("You missed!\n");
                printField(field);
                break;
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
}
