import java.util.Scanner;

/**
 * Author: Connor P Grady
 * Born: 3/11/2015
 */
public class Driver {

    // Main
    public static void main(String[] args) {
        System.out.println("Welcome to set arithmetic.");

        Set<Integer> s1 = new Set<Integer>();
        s1.add(2);
        s1.add(1);
        s1.add(3);
        s1.add(5);

        System.out.println(s1.get(2));
    }
}
