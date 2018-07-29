package pl.coderslab.admin;

import pl.coderslab.model.Exercise;

import java.util.ArrayList;
import java.util.Scanner;

public class ExerciseAdmin {

    private static Scanner scan = new Scanner(System.in);


    public static void launch() {

        boolean exit = false;
        while (!exit) {

            displayAllExercises();

            String choice;
            choice = getString("\nType one of the following options:" +
                    "\n\nadd    - to add new Exercise" +
                    "\n\nedit   - to edit Exercise" +
                    "\n\ndelete - to delete Exercise" +
                    "\n\nquit   - to exit Exercise Administration");
            switch (choice){
                case "add":
                    addExercise();
                    break;
                case "edit":
                    editExercise();
                    break;
                case "delete":
                    deleteExercise();
                    break;
                case "quit":
                    exit = true;
                    System.out.println("--Done--");
                    break;
                default:
                    System.out.println("\nInvalid action, please try again\n");
            }
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

    private static void addExercise(){
        System.out.println("\n...Adding new Exercise...");
        String title = getString("\nType title: ");
        String description = getString("\nType description: ");
        Exercise exercise = new Exercise(title,description);
        exercise.saveToDB();
        System.out.println("\nExercise added");
    }

    private static void editExercise(){
        System.out.println("\n...Editing Exercise...");
        int id = getInt("\nType Exercise id: ");
        Exercise exercise = Exercise.loadById(id);
        if (exercise==null) {
            System.out.println("\nExercise does not exist so cannot be edited.");
        }else {
            exercise.setTitle(getString("\nType title: "));
            exercise.setDescription(getString("\nType description: "));
            exercise.saveToDB();
            System.out.println("\nExercise edited");
        }
    }

    private static void deleteExercise(){
        int id = getInt("\nType id of Exercise to delete this Exercise: ");
        Exercise exercise = Exercise.loadById(id);
        if (exercise==null){
            System.out.println("Exercise does not exist so cannot be deleted.");
        }else {
            exercise.delete();
            System.out.println("\nExercise deleted");
        }
    }

}
