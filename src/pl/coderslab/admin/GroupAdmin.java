package pl.coderslab.admin;

import pl.coderslab.model.Group;

import java.util.ArrayList;
import java.util.Scanner;

public class GroupAdmin {

    private static Scanner scan = new Scanner(System.in);


    public static void launch() {

        boolean exit = false;
        while (!exit) {
            System.out.println("--------------Groups List--------------");
            displayAllGroups();

            String choice;
            choice = getString("\nType one of the following options:" +
                    "\n\nadd    - to add new Group" +
                    "\n\nedit   - to edit Group" +
                    "\n\ndelete - to delete Group" +
                    "\n\nquit   - to exit Group Administration");
            switch (choice){
                case "add":
                    addGroup();
                    getString("Press any key to continue");
                    break;
                case "edit":
                    editGroup();
                    getString("Press any key to continue");
                    break;
                case "delete":
                    deleteGroup();
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

    private static void displayAllGroups(){

        ArrayList<Group> groups = Group.loadAll();
        System.out.println();
        for (Group group : groups){
            System.out.println(group);
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

    private static void addGroup(){
        System.out.println("\n...Adding new Group...");
        String name = getString("\nType name: ");
        Group group = new Group(name);
        group.saveToDB();
        System.out.println("\nGroup added");
    }

    private static void editGroup(){
        System.out.println("\n...Editing Group...");
        int id = getInt("\nType Group id: ");
        Group group = Group.loadById(id);
        if (group==null) {
            System.out.println("\nGroup does not exist so cannot be edited.");
        }else {
            group.setName(getString("\nType name: "));
            group.saveToDB();
            System.out.println("\nGroup edited");
        }
    }

    private static void deleteGroup(){
        int id = getInt("\nType id of Group to delete this Group: ");
        Group group = Group.loadById(id);
        if (group==null){
            System.out.println("Group does not exist so cannot be deleted.");
        }else {
            group.delete();
            System.out.println("\nGroup deleted");
        }
    }

}

