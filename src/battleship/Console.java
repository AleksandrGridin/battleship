package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Console {
    private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

    public static String read() {
        String line = "";
        try {
            line = sc.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static void exit() throws IOException {
        sc.close();
    }

}
