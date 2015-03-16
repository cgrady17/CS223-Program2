import java.io.*;
import java.util.*;

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
            command = tokens[0];
            if (command == "add") {
                String[] commArgs = tokens[1].split(":");
                String setname = commArgs[0];
                for (Set set : setColl) {
                    if (set.getName() == setname) {
                        System.out.println("Error: A set with the request name already exists.");
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
                    if (tokens.length == 2) {
                        // User specified a setname
                        for (Set set : setColl) {
                            if (set.getName() == tokens[1]) {
                                System.out.println(set.toString());
                            }
                        }

                    } else if (tokens.length > 2) {
                        // User did not specify a setname, list all sets
                        StringBuilder strBuilder = new StringBuilder();
                        for (Set set : setColl) {
                            strBuilder.append(set.toString() + ",");
                        }
                        System.out.println(String.valueOf(strBuilder));
                    }
                } else if (command == "intersect") {
                    // Display intersection of <setname1> and <setname2>
                } else if (command == "union") {
                    // Display the union of <setname1> and <setname2>
                } else if (command == "subtract") {
                    // Display the subraction of <setname1> and <setname2>
                } else if (command == "difference") {
                    // Display the difference of <setname1> and <setname2>
                } else if (command == "find") {
                    for (Set set : setColl) {
                        if (set.getName() == tokens[1]) {
                            try {
                                set.indexOf(tokens[2]);
                            } catch (NoSuchElementException nse) {
                                System.out.println("Error: Target not found in Set");
                            }
                        }
                    }
                } else if (command == "equal") {
                    Set set1 = null;
                    Set set2 = null;
                    for (Set set : setColl) {
                        if (set.getName() == tokens[1]) {
                            set1 = set;
                        } else if (set.getName() == tokens[2]) {
                            set2 = set;
                        }
                    }
                    if (set1.equals(set2)) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                } else if (command == "save") {
                    String filename = tokens[1];
                    try {
                        FileOutputStream fos = new FileOutputStream(filename);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(setColl);
                        oos.close();
                    } catch (FileNotFoundException fnf) {
                        System.out.println("Error: Specified file not found!");
                    } catch (IOException e) {
                        System.out.println("Error: IO Error!");
                        System.out.println(e.getMessage());
                    }
                } else if (command == "load") {
                    String filename = tokens[1];
                    try {
                        FileInputStream fis = new FileInputStream(filename);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        setColl = (List<Set>) ois.readObject();
                        ois.close();
                    } catch (ClassNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: Specified file not found!");
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                } else if (command == "exit" || command == "-1" || command == "bye") {
                    System.out.println("Good-bye!");
                    cont = false;
                } else {
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
