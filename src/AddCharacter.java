import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class AddCharacter {
    ArrayList<Character> charList = new ArrayList<Character>();

    public static void main(String[] args) {
        int userNum = welcomeText();

        switch(userNum) {
            case 1:
                break;
            case 2:
                break;
                    /*try {
                        addCharactersToFile();
                    } catch (FileNotFoundException e) {
                        System.out.println("Whoops! Looks like that chapter hasn't been created yet!");
                        System.exit(1);
                    }*/
            case 3:
                createNewFile();
        }

    }

    public static void createNewFile(int num) throws FileNotFoundException {

    }

    public static int getChapterNumber() {
        int userNum = 0;
        Scanner scan = new Scanner(System.in);
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


        scan.close();

        return userNum;

    }

    public static void addCharactersToFile() throws FileNotFoundException {
        int num = getChapterNumber();

        File file = new File(".charfiles/Chapter" + num + ".txt");
        PrintStream print = new PrintStream(new FileOutputStream(file, true));

        print.close();

    }

    public static void getCharactersToAdd() {
        Scanner scan = new Scanner(System.in);
        String line = "";

        System.out.println("Input the character and translation you would like to add separated by a space! ");
        line = scan.nextLine();

        String character = "";
        String translation = "";
        Scanner lineScanner = new Scanner(line);

        character = lineScanner.next();
        translation = lineScanner.next();

        Character newChar = new Character(character, translation);

        scan.close();

    }

    public static void createNewFile() {
        Scanner scan = new Scanner(System.in);

        int userNum = getChapterNumber();

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

    public static int welcomeText() {
        Scanner scan = new Scanner(System.in);

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

        return userInput;
    }

}