import java.io.File;
import java.io.FileNotFoundException;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class CharacterQuiz {

    private static ArrayList<Character> quiz = new ArrayList<Character>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            readFile(scan);
        } catch (FileNotFoundException e) {
            System.out.println("Whoops! That file doesn't exist!");
            System.exit(1);
        }

        quiz(scan);

        scan.close();

    }

    public static int getChapterNumber(Scanner scan) {
        int userNum = 0;
        System.out.print("What chapter would you like to practice? ");

        boolean thrown = true;
        while (thrown) {
            try {
                userNum = scan.nextInt();
                thrown = false;
            } catch (InputMismatchException e) {
                System.out.println("Whoops! Looks like you didn't enter a number! Please try again.");
            }
        }

        return userNum;

    }

    private static void readFile(Scanner scan) throws FileNotFoundException {

        int userNum = getChapterNumber(scan);

        File file = new File("./charfiles/chapter" + userNum + ".txt");
        Scanner fileScan = new Scanner(file);

        while (fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            String[] splitLine = line.split(" ");

            quiz.add(new Character(splitLine[0], splitLine[1]));
        }

        fileScan.close();

    }

    private static int getQuizType(Scanner scan) {
        int userNum = 0;

        System.out.print("Would you like to see:\n " +
                        "1. The Chinese characters...\n " +
                        "2. The English definitions...?");

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

    private static void quiz(Scanner scan) {
        int quizType = getQuizType(scan);

        switch (quizType) {
            case 1:
                chineseCharQuiz(scan);
                break;
            case 2:
                englishDefinitionQuiz(scan);
                break;
        }
    }

    private static void chineseCharQuiz(Scanner scan) {

        while (!(quiz.isEmpty())) {
            for (int i = 0; i < quiz.size(); i++) {
                System.out.println("Character: " + quiz.get(i).getCharacter());
                String answer = scan.nextLine();

                if (answer.equals(quiz.get(i).getMeaning())) {
                    System.out.println("Correct!");
                    quiz.remove(i);
                } else {
                    System.out.println("Not quite :(");
                }
            }
        }
    }

    private static void englishDefinitionQuiz(Scanner scan) {

        while (!(quiz.isEmpty())) {
            for (int i = 0; i < quiz.size(); i++) {
                System.out.println("Definition: " + quiz.get(i).getMeaning());
                String answer = scan.nextLine();

                if (answer.equals(quiz.get(i).getCharacter())) {
                    System.out.println("Correct!");
                    quiz.remove(i);
                } else {
                    System.out.println("Not quite :(");
                }
            }
        }
    }

}
