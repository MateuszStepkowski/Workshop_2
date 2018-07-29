package pl.coderslab.admin;

import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserApp {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        long userId = 0;
        try {
            userId = Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
        }

        User user = User.loadById(userId);
        String choice = null;
        if (user == null) {
            System.out.println("Sorry, User with entered id does not exist");
        } else {
            while (true) {
                choice = getString(
                        "\nType one of the following options:" +
                                "\n\nnadd   - to add new Solution" +
                                "\n\nview   - to display Your Solutions" +
                                "\n\nquit   - to exit Your Solution Administrator");
                if (choice.equals("add")) {
                    displayNonSolved(userId);
                    int exerciseId = getInt("\nEnter id of Exercise which You would like to solve: ");
                    Solution solution = Solution.loadByExerciseId_User_Id(exerciseId, userId);
                    solution.setDescription(getString("\nEnter your solution:\n"));
                    solution.saveToDB();
                    System.out.println("Solution added");
                    getString("Press any key to continue");

                } else if (choice.equals("view")) {
                    displayUserSolutions(userId);
                    getString("Press any key to continue");

                } else if (choice.equals("quit")) {
                    System.out.println("==DONE==");
                    System.exit(0);

                } else {
                    System.out.println("\nINVALID action, please try again\n");
                    getString("Press any key to continue");
                }
            }
        }

    }

    private static String getString(String stringExpectations) {
        System.out.println('\n' + stringExpectations + "\n");
        return scan.nextLine().trim();
    }

    private static int getInt(String intExpectations) {
        System.out.println(intExpectations + "\n");
        int result = 0;
        while (result < 1) {
            try {
                result = Integer.parseInt(scan.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, please try again\n");
            }
        }
        return result;
    }

    private static void displayNonSolved(long userId) {
        ArrayList<Exercise> exercises = Exercise.loadAllNonSolvedByUserId(userId);
        if (exercises == null) {
            System.out.println("You do not have any non-solved exercises");
        } else {
            System.out.println("\n--------------Your non-solved Exercises List--------------\n");
            for (Exercise exercise : exercises) {
                System.out.println(exercise);
            }
        }
    }

    private static void displayUserSolutions(long userId){

        ArrayList<Solution> solutions = Solution.loadAllByUserId(userId);
        if (solutions==null){
            System.out.println("You do not have any solutions yet");
        }else {
            for (Solution solution : solutions){
                System.out.println(solution);
            }
        }

    }
}
