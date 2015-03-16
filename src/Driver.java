import java.io.*;
import java.util.*;

/**
 * Author: Connor P Grady
 * Born: 3/11/2015
 */
public class Driver {

    /**
     * "Main" method of Program; Takes user's commands, outputs result
     * @param args Unused string array of arguments
     */
    public static void main(String[] args) {
        // Write startup "Welcome" string
        System.out.println("Welcome to set arithmetic.");

        // Create empty array of Set objects
        List<Set> setColl = new ArrayList<Set>();
        // Define command variable (to be used later)
        String command;
        // Define cont variable and initialize to true
        boolean cont = true;

        // Create Input Scanner
        Scanner s = new Scanner(System.in);
        while (cont) {
            // Output precedent string
            System.out.print("command>");
            // Get input from Scanner
            String line = s.nextLine().trim();
            // Create string array of input with whitespace as delimiter (using RegEx)
            String[] tokens = line.split("\\s+");
            // Initialize command to first element in tokens array
            command = tokens[0];
            /**
             * The following if/else if/else loop(s) will be my way of interpreting the user's
             * inputted command. Any input not matching one of the ifs is considered invalid.
             * NOTE: I use @param within the commenting below to refer to the "arguments" following
             * the commands from the User.
             */
            if (command.equals("add")) {
                /**
                 * Adds a new Set to the Set Collection with the argued name
                 * @param setname:set The name of the desired Set:The desired Set
                 */
                // Define a variable to say whether the Set already exists or not
                boolean exists = false;
                /**
                 * The param for this takes the following format: setname:set
                 * So, I'll create a String array of elements from the param delimited by a ":"
                 */
                String[] commArgs = tokens[1].split(":");
                // The name of the desired Set is the first element of the commArgs array
                String setname = commArgs[0];
                // Iterate over setColl to find desired Set
                for (Set set : setColl) {
                    // Check if current Set object's name matches the user's specified name
                    if (set.getName().equals(setname)) {
                        // If it does, then the set already exists, output to user
                        System.out.println("Error: A set with the request name already exists.");
                        // Set exists to true
                        exists = true;
                        // Break the loop
                        break;
                    }
                }
                // If the set doesn't already exist, add it to the collection
                if (!exists) {
                    // Create a temporary set by parsing the user's input (@see method "parse" below)
                    Set<Integer> thisSet = parse(tokens[1]);
                    // Add the temporary set to the collection
                    setColl.add(thisSet);
                }
            } else if (command.equals("remove")) {
                /**
                 * There are two "remove" commands, one with one argument, and one with two arguments
                 * The single argument command removes the set with the argued name
                 * The two argument command remove the object at the argued index in the Set with the argued name
                 */
                if (tokens.length == 2) {
                    /**
                     * @param setname The name of the desired Set
                     */
                    // Iterate over the Set collection
                    for (Set set : setColl) {
                        // Find the set matching the desired Set name
                        if (set.getName().equals(tokens[1])) {
                            // Remove the matched set from the collection
                            setColl.remove(set);
                        }
                    }
                } else if (tokens.length == 3) {
                    /**
                     * @param setname The name of the desired Set
                     * @param index The position of the element to be removed
                     */
                    // Iterate over the Set collection
                    for (Set set : setColl) {
                        // Find the set matching the desired Set name
                        if (set.getName().equals(tokens[1])) {
                            /**
                             * Because we are parsing an Int, its safest not to just assume that the input
                             * will always be an Integer. So I'm using a try catch to handle the case where
                             * a user inputs anything that can't be parsed as an Int.
                             */
                            try {
                                // Define the index to be the argued #, by parsing the string as Int
                                int index = Integer.parseInt(tokens[2]);
                                // Remove the object at the specified index
                                set.remove(index);
                            } catch (NumberFormatException nfe) {
                                // User has input an invalid index string, can't be parsed as Int
                                System.out.println("Error: Invalid Index specified in Command.");
                                // Break loop
                                break;
                            }
                        }
                    }
                } else if (command.equals("tremove")) {
                    /**
                     * Removes the argued target from the Set with the argued name
                     * @param setname The name of the desired Set
                     * @param target The object to be removed from the Set
                     */
                    // Iterate over the Set collection
                    for (Set set : setColl) {
                        // Find the Set matching the desired Set name
                        if (set.getName().equals(tokens[1])) {
                            // Remove the argued target from the Set
                            set.remove(tokens[2]);
                        }
                    }
                } else if (command.equals("list")) {
                    /**
                     * W/ argument: Displays the Set with the argued name
                     * W/o argument(s): Displays all Sets in the Set Collection
                     */
                    if (tokens.length == 2) {
                        /**
                         * @param setname The name of the desired Set
                         */
                        // Iterate of the Set collection
                        for (Set set : setColl) {
                            // Find the Set matching the desired Set name
                            if (set.getName().equals(tokens[1])) {
                                // Write the string representation of the Set
                                System.out.println(set.toString());
                            }
                        }
                    } else if (tokens.length > 2) {
                        /**
                         * No params, output all lists in collection
                         */
                        // Create StringBuilder object to develop output string
                        StringBuilder strBuilder = new StringBuilder();
                        // Iterate over the Set collection
                        for (Set set : setColl) {
                            // For each set in collection, append it's string representation to the String Builder
                            strBuilder.append(set.toString()).append(",");
                        }
                        // Write the string value of the String Builder object
                        System.out.println(String.valueOf(strBuilder));
                    }
                } else if (command.equals("intersect")) {
                    /**
                     * Displays the intersection of the two argued sets
                     * @param setname1 The name of the first desired Set
                     * @param setname2 The name of the second desired Set
                     */
                } else if (command.equals("union")) {
                    /**
                     * Displays the union of the two argued sets
                     * @param setname1 The name of the first desired Set
                     * @param setname2 The name of the second desired Set
                     */
                } else if (command.equals("subtract")) {
                    /**
                     * Displays the subraction of the two argued sets
                     * @param setname1 The name of the first desired Set
                     * @param setname2 The name of the second desired Set
                     */
                } else if (command.equals("difference")) {
                    /**
                     * Displays the difference of the two argued sets
                     * @param setname1 The name of the first desired Set
                     * @param setname2 The name of the second desired Set
                     */
                } else if (command.equals("find")) {
                    /**
                     * Finds the argued object in the Set with the argued name and writes the
                     * index of that object in the Set
                     * @param setname The name of the desired Set
                     * @param target The object of which to get the index
                     */
                    // Iterate over the Set collection
                    for (Set set : setColl) {
                        // Find the Set matching the desired Set name
                        if (set.getName().equals(tokens[1])) {
                            // Method indexOf throws "NoSuchElementException", so use try catch to handle
                            try {
                                // Write the index of the argued target
                                System.out.println(set.indexOf(tokens[2]));
                            } catch (NoSuchElementException nse) {
                                // Gracefully write that the argued target does not exist
                                System.out.println("Error: Target not found in Set");
                            }
                        }
                    }
                } else if (command.equals("equal")) {
                    /**
                     * Writes "True" if the argued pair of Sets have identical elements
                     * Otherwise, writes "False"
                     * @param setname1 The name of the first desired Set
                     * @param setname2 The name of the second desired Set
                     */
                    // Create temp set1 initialized to null
                    Set set1 = null;
                    // Create temp set2 initialized to null
                    Set set2 = null;
                    // Iterate over the Set collection
                    for (Set set : setColl) {
                        // Find the Set matching the first desired Set
                        if (set.getName().equals(tokens[1])) {
                            // Set set1 to the found Set
                            set1 = set;
                            // Find the Set matching the second desired Set
                        } else if (set.getName().equals(tokens[2])) {
                            // Set set2 to the found Set
                            set2 = set;
                        }
                    }
                    // Using Set method "equals", if they equal write "True", otherwise write "False"
                    if (set1 != null && set1.equals(set2)) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                } else if (command.equals("save")) {
                    /**
                     * Extra Credit: Saves the existing Set collection to a file
                     * @param filename The name of the file to which the method should write
                     */
                    // The file name is the second element in the tokens array
                    String filename = tokens[1];
                    // Two possible exceptions, use try catch
                    try {
                        // Create FileOutputStream object writing to the argued file
                        FileOutputStream fos = new FileOutputStream(filename);
                        // Create ObjectOutputStream object writing to the FileOutputStream
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        // Write the Set Collection object (type List<Set>) to the ObjectOutputStream
                        oos.writeObject(setColl);
                        // Close the ObjectOutputStream
                        oos.close();
                    } catch (FileNotFoundException fnf) {
                        // Argued file not found, gracefully handle
                        System.out.println("Error: Specified file not found!");
                    } catch (IOException e) {
                        // Error writing to specified file (other than "Not Found")
                        System.out.println("Error: IO Error!");
                        System.out.println(e.getMessage());
                    }
                } else if (command.equals("load")) {
                    /**
                     * Extra Credit: Load the argued file and set its contents to the Set Collection
                     * @param filename The name of the file to load
                     */
                    // The file name is the second element in the tokens array
                    String filename = tokens[1];
                    // Three possible exceptions, use try catch
                    try {
                        // Create FileInputStream reading from the argued file
                        FileInputStream fis = new FileInputStream(filename);
                        // Create ObjectInputStream reading from the FileInputStream
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        // Set the Set Collection to the read object
                        setColl = (List<Set>) ois.readObject();
                        // Close the ObjectInputStream
                        ois.close();
                    } catch (ClassNotFoundException e) {
                        // Shouldn't happen, but could.
                        System.out.println("Error: " + e.getMessage());
                    } catch (FileNotFoundException e) {
                        // Argued file not found, gracefully handle
                        System.out.println("Error: Specified file not found!");
                    } catch (IOException e) {
                        // Error reading from argued file (other than "Not Found)
                        System.out.println("Error: " + e.getMessage());
                    }
                } else if (command.equals("exit") || command.equals("-1") || command.equals("bye")) {
                    /**
                     * Exit from the program; No args.
                     * Expanded past requirements to include other relevant exit strings
                     */
                    // Write the exit message
                    System.out.println("Good-bye!");
                    // Set cont to false to exist while loop
                    cont = false;
                } else {
                    /**
                     * User input didn't match any specified commands, means their input was invalid
                     */
                    // Write that the input command was invalid
                    System.out.println("Error: Invalid Command");
                }
            }
        }
    }

    /**
     * Parse a string representation of a Set object, using it to create a new Set object and return it
     * @param text The user's string representation of a Set object
     * @return The new Set object (of type Integer)
     */
    public static Set<Integer> parse(String text) {
        /**
         * The param for this takes the following format: setname:set
         * So, I'll create a String array of elements from the param delimited by a ":"
         */
        String[] commArgs = text.split(":");
        // The name of the desired Set is the first element of the commArgs array
        String setName = commArgs[0];
        // Create new empty Set
        Set<Integer> set = new Set<Integer>(setName);
        // Create String Tokenizer with the second elemetn int he commArgs array
        StringTokenizer tokenizer = new StringTokenizer(commArgs[1]);
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
                /**
                 * We're here if the token could not be parsed to an Int
                 * We do nothing because we only want to add Integers to the Set
                 */
            }
        }
        // Return the new Set
        return set;
    }
}
