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
                    int caonumber=login();
                    doLoginMenuLoop(caonumber);
                    break;
            }
            printMainMenu();
            menuOption = getMainMenuOption();

        }
        System.out.println("Quit Application Chosen");

    }

    private void register()
    {
        int caoNumber=0;
        String dob = "";
        String password = "";

        boolean input = false;
        while(!input)
        {
            System.out.println("Enter in Your 5 Digit CaoNumber");
            caoNumber = sc.nextInt();
            if (RegexChecker.isValidCao(caoNumber)) {

                input = true;
            }
        }
        input = false;
        while(!input){
            System.out.println("Enter in your DOB (YYYY-MM-DD)");
            dob = sc.next();
            if (RegexChecker.isValidDOB(dob)) {

                input = true;
            }
        }

        input = false;
        while(!input){
            System.out.println("Enter in a Strong password");
            System.out.println();
            System.out.println(Colours.GREEN+"- At least 8 charataers containing at least one digit," + Colours.RESET);
            System.out.println(Colours.GREEN+"- One lowercase and one uppercase letter,"+ Colours.RESET);
            System.out.println(Colours.GREEN+"- One special character"+ Colours.RESET);


            password = sc.next();
            if (RegexChecker.isValidPass(password)) {

                input = true;
            }
        }


        String message = "REGISTER" + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER +
                dob + CAOService.BREAKING_CHARACTER + password;
        System.out.println("Message ready for server: "+ message);


    }
    private int login()
    {
        int caoNumber=0;
        String dob = "";
        String password = "";

        boolean input = false;
        while(!input)
        {
            System.out.println("Enter in Your 5 Digit CaoNumber");
             caoNumber = sc.nextInt();
            if (RegexChecker.isValidCao(caoNumber)) {

                input = true;
            }
        }
        input = false;
        while(!input){
            System.out.println("Enter in valid DOB (YYYY-MM-DD)");
            dob = sc.next();
            if (RegexChecker.isValidDOB(dob)) {

                input = true;
            }
        }

        input = false;
        while(!input){
            System.out.println("Enter in a Strong password");
            System.out.println();
            System.out.println(Colours.GREEN+"- At least 8 charataers containing at least one digit," + Colours.RESET);
            System.out.println(Colours.GREEN+"- One lowercase and one uppercase letter,"+ Colours.RESET);
            System.out.println(Colours.GREEN+"- One special character"+ Colours.RESET);


            password = sc.next();
            if (RegexChecker.isValidPass(password)) {

                input = true;
            }
        }


        Student s = new Student(caoNumber,dob,password);
        String message = "LOGIN" + CAOService.BREAKING_CHARACTER + s.getCaoNumber() + CAOService.BREAKING_CHARACTER
                + s.getDayOfBirth() + CAOService.BREAKING_CHARACTER
                + s.getPassword();
        System.out.println("Message ready for server: "+ message);
        return caoNumber;
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
            printMainMenu();
        }
        else
            { menuOption = MainMenu.values()[option];}


        return menuOption;

    }

    private void doLoginMenuLoop(int caoNum)
    {
        printLoginMenu();
        LoginMenu loginOption = getLoginMenuOption();


        while(loginOption != LoginMenu.LOGOUT)
        {
            switch (loginOption){
                case QUIT:
                    System.out.println("goodbye");
                    System.exit(0);
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
            printLoginMenu();

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

        String courseID = "";

        boolean input = false;
        while(!input)
        {
            System.out.println("Enter Course ID (eg DK999)");
            courseID = sc.next();
            if (RegexChecker.isValidCourse(courseID)) {

                input = true;
            }
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
            else{
                System.out.println("Enter Valid Course (e.g DK999)");
                i--;

            }

        }
        String message = "updateCoursesForUse"+ CAOService.BREAKING_CHARACTER + caoNum + CAOService.BREAKING_CHARACTER + choices;

        System.out.println("Message ready for server: "+ message);
    }


}
