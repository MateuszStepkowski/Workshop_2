package pl.coderslab.admin;

import java.util.Scanner;

public class AdminApp {

    private static Scanner scan = new Scanner(System.in);



    public static void main(String[] args) {

        while (true) {

            int choice=0;

            choice = getInt("\n=======-----  PROGRAMMING SCHOOL MENU  -----=======" +
                    "\n---------------------------------------------------" +
                    "\n\nPlease enter number: " +
                    "\n( 1 ) -  for Group Administration" +
                    "\n( 2 ) -  for User Administration" +
                    "\n( 3 ) -  for Exercise Administration" +
                    "\n( 4 ) -  for Solution Administration" +
                    "\n( 5 ) -  to EXIT\n");

            switch (choice){

                case 1: GroupAdmin.launch();
                        getString("\nPress any key to continue");

                case 2: UserAdmin.launch();
                        getString("\nPress any key to continue");

                case 3: ExerciseAdmin.launch();
                        getString("\nPress any key to continue");

                case 4: SolutionAdmin.launch();
                        getString("\nPress any key to continue");

                case 5:
                    System.out.println("\n\nGood Bye !");
                    System.exit(0);

                default:
                    System.out.println("\nUnsupported choice, please try again");
                    getString("\nPress any key to continue");

            }

        }
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

    private static String getString(String stringExpectations){
        System.out.println(stringExpectations+"\n");
        return scan.nextLine().trim();
    }



}
