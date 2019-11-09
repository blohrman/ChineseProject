import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Class that allows the user to choose what they want to do and goes from there.
 *
 * @author Ben Jammin'
 */
public class Menu {

    /**
     * ArrayList used to write to file.
     */
    private static ArrayList<Character> charList = new ArrayList<Character>();

    /**
     * Main functionality.
     *
     * @param args - takes no args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int userNum = welcomeText(scan);

        switch(userNum) {
            case 1:
                CharacterQuiz.main(null);
                break;
            case 2:
                try {
                    addCharactersToFile(scan);
                } catch (FileNotFoundException e) {
                    System.out.println("Whoops! Looks like that chapter hasn't been created yet!");
                    System.exit(1);
                }
                break;
            case 3:
                createNewFile(scan);
        }

        scan.close();

    }

    /**
     * Gets the number of the chapter the user wants to study/create vocabulary for.
     *
     * @param scan - scanner for user input
     * @return - returns the chapter as an integer
     */
    public static int getChapterNumber(Scanner scan) {
        int userNum = 0;
        System.out.print("What chapter are you adding vocabulary to/creating? ");

        boolean thrown = true;
        while (thrown) {
            try {
                userNum = scan.nextInt();
                thrown = false;
            } catch (InputMismatchException e) {
                System.out.println("Whoops! Looks like you didn't enter a number! Please try again.");
            }
        }

        scan.nextLine();

        return userNum;

    }

    /**
     * Adds the character objects to the specified file.
     *
     * @param scan - scanner for user input
     * @throws FileNotFoundException - throws this if the file isn't found
     */
    public static void addCharactersToFile(Scanner scan) throws FileNotFoundException {
        int num = getChapterNumber(scan);

        getCharactersToAdd(scan);

        File file = new File("./charfiles/chapter" + num + ".txt");
        PrintStream print = new PrintStream(new FileOutputStream(file, true));

        for (int i = 0; i < charList.size(); i++) {
            print.println(charList.get(i).getCharacter() + " " + charList.get(i).getMeaning());
        }

        print.close();

    }

    /**
     * Asks the user to input a pair of a Chinese word with an English definition and stores it in an ArrayList.
     *
     * @param scan - scanner for user input
     */
    public static void getCharactersToAdd(Scanner scan) {
        String line = "";

        System.out.print("Input the character and translation you would like to add separated by a space! ");
        line = scan.nextLine();

        String character = "";
        String translation = "";
        Scanner lineScanner = new Scanner(line);

        character = lineScanner.next();
        translation = lineScanner.next();

        Character newChar = new Character(character, translation);

        charList.add(newChar);

        String cont = "";
        System.out.print("Would you like to add another character? ");
        cont = scan.next();

        if (cont.equals("y")) {
            getCharactersToAdd(scan);
        }

    }

    /**
     * Creates a new file if the user requests to.
     *
     * @param scan - scanner for user input
     */
    public static void createNewFile(Scanner scan) {
        int userNum = getChapterNumber(scan);

        scan.nextLine();

        try {
            File file = new File("./charfiles/chapter" + userNum + ".txt");

            if (file.createNewFile()) {
                System.out.println("Nice! The file was created successfully!");
            } else {
                System.out.println("That file already exists! Maybe you were trying to add new vocab?");
            }

        } catch (IOException e) {
            System.exit(1);
        }

    }

    /**
     * Prints welcome menu.
     *
     * @param scan - scanner for user input
     * @return - returns the user's selection of what to do
     */
    public static int welcomeText(Scanner scan) {
        int userInput = 0;
        System.out.println("Hello! Would you like to: \n" +
                "1. Practice existing vocabulary...\n" +
                "2. Add new vocabulary...\n" +
                "3. Create a new vocabulary file...?");

        userInput = scan.nextInt();
        while (userInput > 3) {
            System.out.println("Please enter 1, 2, or 3.");
            userInput = scan.nextInt();
        }

        scan.nextLine();

        return userInput;
    }

}