package com.dkit.oopca5.client;

/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */
import com.dkit.oopca5.core.CAOService;
import com.dkit.oopca5.core.Colours;
import com.dkit.oopca5.core.MenuException;
import com.dkit.oopca5.core.Student;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);

            System.out.println("Client message: The Client is running and has connected to the server");


            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply


            doMainMenuLoop(socketWriter,socketReader);

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }

    private void doMainMenuLoop(PrintWriter socketWriter,Scanner socketReader)
    {


        printMainMenu();
        MainMenu menuOption = getMainMenuOption();

        while(menuOption != MainMenu.QUIT_APPLICATION)
        {
            switch (menuOption){
                case REGISTER:
                    System.out.println("Register");
                    register(socketWriter,socketReader);
                    break;

                case LOGIN:
                    System.out.println("LOGIN");
                    login(socketWriter,socketReader);
                    break;
            }
            printMainMenu();
            menuOption = getMainMenuOption();

        }
        System.out.println("Quit Application Chosen");

    }

    private void register(PrintWriter socketWriter,Scanner socketReader)
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
//        System.out.println("Message ready for server: "+ message);

        /**Client/Server*/
        socketWriter.println(message);
        String response = socketReader.nextLine();
        System.out.println(response);

        if(response.equals(CAOService.SUCCESSFUL_REGISTER)){
            System.out.println("Student Registered");

        }else if(response.equals(CAOService.FAILED_REGISTER)){

        System.out.println("Student Registration Failed");

        }else if(response.equals(CAOService.Already_Registered)){

            System.out.println("Student already registered");
        }


    }
    private int login(PrintWriter socketWriter,Scanner socketReader)
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
//        System.out.println("Message ready for server: "+ message);

        socketWriter.println(message);
        String response = socketReader.nextLine();
        System.out.println(response);

        if(response.equals(CAOService.LOGIN_SUC)){
            System.out.println("Login Successful");
            doLoginMenuLoop(caoNumber,socketWriter,socketReader);

        }else if(response.equals(CAOService.LOGIN_FAIL)){
//            doMainMenuLoop(socketWriter,socketReader);
        }

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

    private void doLoginMenuLoop(int caoNum,PrintWriter socketWriter,Scanner socketReader)
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
                    displayCourse(socketWriter,socketReader);
                    break;
                case DISPLAY_ALL_COURSES:
                    displayAllCourses(socketWriter,socketReader);
                    break;
                case DISPLAY_CURRENT_CHOICES:
                    displayCurrentChoices(caoNum,socketWriter,socketReader);
                    break;
                case UPDATE_CURRENT_CHOICES:
                    updateCurrentChoices(caoNum,socketWriter,socketReader);
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
    private void displayCourse(PrintWriter socketWriter,Scanner socketReader)
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
        String message = "DISPLAY COURSE"+CAOService.BREAKING_CHARACTER+courseID;
        socketWriter.println(message);
        String response = socketReader.nextLine();
        if(response.equals(CAOService.FIND_COURSE_FAIL)){
            System.out.println("No course found with that ID");
        }else{
            String[] components = response.split(CAOService.BREAKING_CHARACTER);

            String id = components[0];
            String level = components[1];
            String title = components[2];
            String intitudte = components[3];

            System.out.println("Course:"+id+" Level:"+level+" Title:"+title+" Institute:"+ intitudte);

        }

    }

    private void displayAllCourses(PrintWriter socketWriter,Scanner socketReader)
    {
        String message = "DISPLAY_ALL";
//        System.out.println("Message ready for server: "+ message);

        socketWriter.println(message);
        String response = socketReader.nextLine();
        if(response == CAOService.DISPLAY_ALL_FAIL){
            System.out.println("No Courses Found");
        }else{
            String[] components = response.split(CAOService.BREAKING_CHARACTER);
            for(String s : components){
                System.out.println(s);
            }

        }
    }

    private void displayCurrentChoices(int caoNum,PrintWriter socketWriter,Scanner socketReader)
    {
        String message = "DISPLAY CURRENT" + CAOService.BREAKING_CHARACTER + caoNum;
        socketWriter.println(message);
        String response = socketReader.nextLine();
        int num = 1;
        if(response == CAOService.DISPLAY_CURRENT_FAIL){
            System.out.println("NO CURRENT CHOICES FOUND FOR USER");

        }else{
            String[] components = response.split(CAOService.BREAKING_CHARACTER);
            for(int i = 0 ; i < components.length ; i ++){
                System.out.println(components[i]);
                num ++;
            }

        }
    }

    private void updateCurrentChoices(int caoNum,PrintWriter socketWriter,Scanner socketReader)
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
        String message = "UPDATE CURRENT"+ CAOService.BREAKING_CHARACTER + caoNum +CAOService.BREAKING_CHARACTER;
        for(String s : choices){
            message += s + CAOService.BREAKING_CHARACTER;
        }
        socketWriter.println(message);
        String response = socketReader.nextLine();

        if(response==CAOService.UPDATE_CURRENT){
            System.out.println("STUDENT CHOICES UPDATED");
        }

    }


}
