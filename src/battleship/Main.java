package battleship;

import battleship.ships.*;
import battleship.users.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Player one = new Player();
        Player two = new Player();
        one.setPlayer(two);
        two.setPlayer(one);

        System.out.println("Player 1, place your ships on the game field\n");
        one.initGame(one.getField());
        one.initGame(one.getFok());
        one.locateShips();
        System.out.println("Press Enter and pass the move to another player");
        Console.read();
        System.out.println("Player 2, place your ships to the game field\n");
        two.initGame(two.getField());
        two.initGame(two.getFok());
        two.locateShips();
        System.out.println("Press Enter and pass the move to another player");
        Console.read();

        while (true) {
            two.printField(two.getFok());
            System.out.println("---------------------");
            two.printField(one.getField());
            System.out.println();
            System.out.println("Player 1, it's your turn:\n");
            one.takeAHit();
            if (one.isWin()) {
                System.out.println("You sank the last ship. Player 1 won. Congratulations!");
                break;
            }
            System.out.println("Press Enter and pass the move to another player");
            Console.read();

            one.printField(one.getFok());
            System.out.println("---------------------");
            one.printField(two.getField());
            System.out.println();

            System.out.println("Player 2, it's your turn:\n");
            two.takeAHit();
            if (two.isWin()) {
                System.out.println("You sank the last ship. Player 2 won. Congratulations!");
                break;
            }
            System.out.println("Press Enter and pass the move to another player");
            Console.read();
        }

        Console.exit();
    }


}
