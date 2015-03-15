import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Author: Connor P Grady
 * Born: 3/11/2015
 */
public class Driver {

    // Main
    public static void main(String[] args) {
        System.out.println("Welcome to set arithmetic.");

        // Create empty array of Set objects
        List<Set> setColl = new ArrayList<Set>();
        String command;
        boolean cont = true;

        // Create Input Scanner
        Scanner s = new Scanner(System.in);
        while (cont) {
            // Output precedent string
            System.out.print("command>");
            // Get input from Scanner
            String line = s.nextLine().trim();
            // Create string array of input with whitespace as delimiter
            String[] tokens = line.split("\\s+");
            // Iterator over objects in string array
            command = tokens[0];
            if (command == "add") {
                String[] commArgs = tokens[1].split(":");
                String setname = commArgs[0];
                for (Set set : setColl) {
                    if (set.getName() == setname) {
                        System.out.println("Error: A set with the request name already exists.");
                        cont = false;
                        break;
                    }
                }
                if (!cont) {
                    Set<Integer> thisSet = parse(commArgs[1]);
                    setColl.add(thisSet);
                }
            } else if (command == "remove") {
                if (tokens.length == 2) {
                    // Command: remove <setname>
                    for (Set set : setColl) {
                        if (set.getName() == tokens[1]) {
                            setColl.remove(set);
                        }
                    }
                } else if (tokens.length == 3) {
                    // Command: remove <setname> <index>
                    for (Set set : setColl) {
                        if (set.getName() == tokens[1]) {
                            try {
                                int index = Integer.parseInt(tokens[2]);
                                set.remove(index);
                            } catch (NumberFormatException nfe) {
                                System.out.println("Error: Invalid Index specified in Command.");
                                cont = false;
                                break;
                            }
                        }
                    }
                } else if (command == "tremove") {
                    for (Set set : setColl) {
                        if (set.getName() == tokens[1]) {
                            set.remove(tokens[2]);
                        }
                    }
                } else if (command == "list") {
                    if (tokens.length > 1) {
                        // User specified a setname

                    } else {
                        // User did not specify a setname, list all sets
                        StringBuilder strBuilder = new StringBuilder();
                        for (Set set : setColl) {
                            strBuilder.append(set.toString() + ",");
                        }
                        System.out.println(String.valueOf(strBuilder));
                    }
                }
                else {
                    System.out.println("Error: Invalid Command");
                }
            }
        }

    }

    public static Set<Integer> parse(String text) {
        // Create new empty Set
        Set<Integer> set = new Set<Integer>();
        // Create String Tokenizer
        StringTokenizer tokenizer = new StringTokenizer(text);
        // While the tokenizer has more tokens...
        while (tokenizer.hasMoreTokens()) {
            // Get next token
            String token = tokenizer.nextToken();
            try {
                // Parse the token to an int
                int i = Integer.parseInt(token);
                // Add the int to the new Set
                set.add(i);
            } catch (NumberFormatException nfe) {
                // Here if parsing to int failed
                // Means the token was not an int
            }
        }
        // Return the new Set
        return set;
    }
}
