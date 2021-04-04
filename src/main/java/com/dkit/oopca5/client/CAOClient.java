package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */
import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Colours;
import com.dkit.oopca5.core.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CAOClient
{
    private static Scanner sc= new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println("Welcome to CAO");
        CAOClient client = new CAOClient();
        client.start();
    }

    private void start()
    {
        doMainMenuLoop();
    }

    private void doMainMenuLoop()
    {

        printMainMenu();
        MainMenu menuOption = getMainMenuOption();

        while(menuOption != MainMenu.QUIT_APPLICATION)
        {
            switch (menuOption){
                case REGISTER:
                    System.out.println("Register");
                    register();
                    break;

                case LOGIN:
                    System.out.println("LOGIN");
                    login();
                    // if login is successful run doLoginMenuLoop
//                    doLoginMenuLoop(caonumber);
                    break;
            }
            printMainMenu();
            menuOption = getMainMenuOption();

        }
        System.out.println("Quit Application Chosen");

    }

    private void register()
    {
        System.out.println("Please Enter details: ");
        System.out.println("CAO NUmber: ");
        int caoNumber = sc.nextInt();
        System.out.println("DOB: ");
        String dob = sc.next();
        System.out.print("Password: ");
        String password = sc.next();
        String message = "REGISTER" + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER +
                dob + CAOService.BREAKING_CHARACTER + password;
        System.out.println("Message ready for server: "+ message);


    }
    private void login()
    {
        System.out.println("CAO NUmber");
        int caoNumber = sc.nextInt();
        System.out.println("DOB");
        String dob = sc.next();
        System.out.print("Password: ");
        String password = sc.next();

        Student s = new Student(caoNumber,dob,password);
        String message = "LOGIN" + CAOService.BREAKING_CHARACTER + s + CAOService.BREAKING_CHARACTER;
        System.out.println("Message ready for server: "+ message);
    }

    private void printMainMenu()
    {
        for (int i =0; i < MainMenu.values().length;i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + MainMenu.values()[i].toString() + Colours.RESET);

        }
        System.out.println("Enter in option (0 to cancel) >");


    }

    private MainMenu getMainMenuOption()
    {
        int option = sc.nextInt();
        MainMenu menuOption = null;
        if (option < 0 || option > 2)
        {
            System.out.println("Please select valid menu option ");
            System.out.println(Colours.RED+"Enter 0 to Quit");
            System.out.println(Colours.RED+"Enter 1 to Register");
            System.out.println(Colours.RED+"Enter 2 to Login");
        }
        else
            { menuOption = MainMenu.values()[option];}


        return menuOption;

    }

    private void doLoginMenuLoop(int caoNum)
    {

        printLoginMenu();
        LoginMenu loginOption = getLoginMenuOption();

        while(loginOption != LoginMenu.QUIT)
        {
            switch (loginOption){
                case LOGOUT:
                    System.out.println("Logged Out");
                    break;
                case DISPLAY_COURSE:
                    displayCourse();
                    break;
                case DISPLAY_ALL_COURSES:
                    displayAllCourses();
                    break;
                case DISPLAY_CURRENT_CHOICES:
                    displayCurrentChoices(caoNum);
                    break;
                case UPDATE_CURRENT_CHOICES:
                    updateCurrentChoices(caoNum);
                    break;
                
            }
            printLoginMenu();
            loginOption = getLoginMenuOption();

        }
        System.out.println("Quit Application Chosen");

    }

    private LoginMenu getLoginMenuOption() {
        int option = sc.nextInt();
        LoginMenu menuOption = null;
        if (option < 0 || option > 5)
        {
            System.out.println("Please select valid menu option ");
            System.out.println(Colours.RED+"Enter 0 to Quit");
            System.out.println(Colours.RED+"Enter 1 to Register");
            System.out.println(Colours.RED+"Enter 2 to Login");
            System.out.println(Colours.RED+"Enter 3 to Login");
            System.out.println(Colours.RED+"Enter 4 to Login");
            System.out.println(Colours.RED+"Enter 5 to Login");
        }
        else
        { menuOption = LoginMenu.values()[option];}


        return menuOption;

    }

    private void printLoginMenu() {
        for (int i =0; i < LoginMenu.values().length;i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + LoginMenu.values()[i].toString() + Colours.RESET);

        }
        System.out.println("Enter in option (0 to cancel) >");

    }
    private void displayCourse()
    {
        System.out.println("Course ID :");
        String courseID = sc.next();

        if(RegexChecker.isValidCourse(courseID)){
            String message = "findCourse" + CAOService.BREAKING_CHARACTER + courseID;
            System.out.println("Message ready for server: "+ message);
        }
        else{
            System.out.println("Invalid Course Entered");
        }
    }

    private void displayAllCourses()
    {
        String message = "findAllCourses";
        System.out.println("Message ready for server: "+ message);
    }

    private void displayCurrentChoices(int caoNum)
    {
        String message = "findCoursesForUser" + CAOService.BREAKING_CHARACTER + caoNum;
        System.out.println("Message ready for server: "+ message);
    }

    private void updateCurrentChoices(int caoNum)
    {
        List<String> choices = new ArrayList<>();
        System.out.println("Enter choices");
        System.out.println("How many Course you entering?");
        int num = sc.nextInt();

        for(int i = 0; i< num; i++)
        {
            System.out.println("Enter choice");
            String id = sc.next();

            if (RegexChecker.isValidCourse(id))
            {
                choices.add(id);
            }
            else if (i == 0)
            {
                i = 0;
            }
            else
            {
                i--;
            }


        }
        String message = "updateCoursesForUse"+ CAOService.BREAKING_CHARACTER + caoNum + CAOService.BREAKING_CHARACTER + choices;

        System.out.println("Message ready for server: "+ message);
    }


}
