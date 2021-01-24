package battleship;

import java.util.Scanner;

public class Console {
    private static Scanner sc = new Scanner(System.in);

    public static String read() {
        String[] line = sc.nextLine().split(" ");
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
