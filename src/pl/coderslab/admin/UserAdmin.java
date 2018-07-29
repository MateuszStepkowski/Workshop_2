package pl.coderslab.admin;

import pl.coderslab.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserAdmin {

    private static Scanner scan = new Scanner(System.in);


    public static void launch() {

        boolean exit = false;
        while (!exit) {

            displayAllUsers();

            String choice;
            choice = getString("\nType one of the following options:" +
                    "\n\nadd    - to add new User" +
                    "\n\nedit   - to edit User" +
                    "\n\ndelete - to delete User" +
                    "\n\nquit   - to exit User Administration");
            switch (choice){
                case "add":
                    addUser();
                    getString("Press any key to continue");
                    break;
                case "edit":
                    editUser();
                    getString("Press any key to continue");
                    break;
                case "delete":
                    deleteUser();
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

    private static void displayAllUsers(){

        System.out.println("--------------Users List--------------");
        ArrayList<User> users = User.loadAll();
        System.out.println();
        for (User user : users){
            System.out.println(user);
        }
    }

    private static String getString(String stringExpectations){
        System.out.println(stringExpectations+"\n");
        return scan.nextLine().trim();
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

    private static void addUser(){
        System.out.println("\n...Adding new User...");
        String username = getString("\nType username: ");
        String password = getString("\nType password: ");
        String email = getString("\nType email: ");
        int groupId = getInt("\nType group_id: ");
        User user = new User(username,password,email,groupId);
        user.saveToDB();
        System.out.println("\nUser added");
    }

    private static void editUser(){
        System.out.println("\n...Editing User...");
        long id = getLong("\nType User id: ");
        User user = User.loadById(id);
        if (user==null) {
            System.out.println("\nUser does not exist so cannot be edited.");
        }else {
            user.setUsername(getString("\nType username: "));
            user.setPassword(getString("\nType password: "));
            user.setEmail(getString("\nType email: "));
            user.setUser_group_id(getInt("\nType group_id: "));
            user.saveToDB();
            System.out.println("\nUser edited");
        }
    }

    private static void deleteUser(){
        long id = getLong("\nType id of User to delete this User: ");
        User user = User.loadById(id);
        if (user==null){
            System.out.println("User does not exist so cannot be deleted.");
        }else {
            user.delete();
            System.out.println("\nUser deleted");
        }
    }

}
