package pl.coderslab.admin;

import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class SolutionAdmin {

    private static Scanner scan = new Scanner(System.in);


    public static void launch() {

        boolean exit = false;
        while (!exit) {

            String choice;
            choice = getString("\nType one of the following options:" +
                    "\n\nadd    - to add  Solution from User" +
                    "\n\nview   - to display Solutions of given User" +
                    "\n\nquit   - to exit Solution Administration");
            switch (choice){
                case "add":
                    addSolution();
                    getString("Press any key to continue");

                    break;
                case "view":
                    displaySolutionsOfUser();
                    getString("Press any key to continue");
                    break;
                case "quit":
                    exit = true;
                    System.out.println("--Done--");
                    break;
                default:
                    System.out.println("\nInvalid action, please try again\n");
                    getString("Press any key to continue");
            }
        }
    }

    private static long getLong(String intExpectations) {
        System.out.println(intExpectations+"\n");
        long result=0;
        while (result<1) {
            try {
                result = Long.parseLong(scan.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, please try again\n");
            }
        }
        return result;
    }

    private static String getString(String stringExpectations){
        System.out.println(stringExpectations+"\n");
        return scan.nextLine().trim();
    }


    private static int getInt(String intExpectations) {
        System.out.println(intExpectations+"\n");
        int result=0;
        while (result<1) {
            try {
                result = Integer.parseInt(scan.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Wrong format, please try again\n");
            }
        }
        return result;
    }


    private static void displayAllUsers(){

        System.out.println("--------------Users List--------------");
        ArrayList<User> users = User.loadAll();
        System.out.println();
        for (User user : users){
            System.out.println(user);
        }
    }

    private static void displayAllExercises(){

        System.out.println("--------------Exercises List--------------");
        ArrayList<Exercise> exercises = Exercise.loadAll();
        System.out.println();
        for (Exercise exercise : exercises){
            System.out.println(exercise);
        }
    }


    private static void addSolution(){
        System.out.println("\n...Adding new Solution...");
        displayAllUsers();
        long user_id = getLong("\nType User id: ");
        displayAllExercises();
        int exercise_id = getInt("\nType Exercise id: ");
        Solution solution = new Solution(exercise_id,user_id);
        solution.saveToDB();
        System.out.println("\nSolution added");
    }



    private static void displaySolutionsOfUser(){

        displayAllUsers();
        long user_id = getLong("\nType User id to display all Solutions of this User: ");
        ArrayList<Solution> solutions = Solution.loadAllByUserId(user_id);
        if (solutions==null){
            System.out.println("There is no solution with such user_id.");
        }else {
            for (Solution solution : solutions){
                System.out.println(solution);
            }
        }

    }

}
